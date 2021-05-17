/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.transaction.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionManager;
import org.springframework.util.ObjectUtils;

/**
 * Abstract class that implements a Pointcut that matches if the underlying
 * {@link TransactionAttributeSource} has an attribute for a given method.
 *
 * @author Juergen Hoeller
 * @since 2.5.5
 */
@SuppressWarnings("serial")
// 找切入点，主要是判断类的方法上是否有 @Transactional 注解。
abstract class TransactionAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

	protected TransactionAttributeSourcePointcut() {
		setClassFilter(new TransactionAttributeSourceClassFilter());
	}

	/**
	 * 执行 AbstractFallbackTransactionAttributeSource#getTransactionAttribute()方法：
	 * 1. 先看缓存中有没有 targetClass 的事物属性(TransactionAttribute)，如果有，直接返回事物属性。
	 * 2. (如果上面没有) 能否从目标类的方法上解析出事物属性，
	 * 3. (如果上面解析不出来) 能否从目标类上解析出事务属性，
	 * 4. (如果上面解解析不出来) 能否从目标类的父接口中的对应方法中解析出事务属性，
	 * 5. (如果上面解析不出来) 能否从目标类的父接口上解析出事务属性。
	 *
	 * 上面 2-5 中有任意一步能解析出事务属性，下面的步骤不再执行。并且将解析到的事物属性保存到缓存，最后返回。
	 * PS1: 但凡被 @Transactional 注解标注了，且是 public 的，就能解析出事务属性。
	 * PS2：@Transactional 注解的解析底层是调用
	 * 		SpringTransactionAnnotationParser#parseTransactionAnnotation(AnnotatedElement element) 方法
	 * 		实现的。
	 * PS3: AnnotationTransactionAttributeSource 继承了 AbstractFallbackTransactionAttributeSource，所以
	 * 		解析到的属性信息也保存在 AnnotationTransactionAttributeSource 的 attributeCache 属性中。
	 *
	 */

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		TransactionAttributeSource tas = getTransactionAttributeSource();
		return (tas == null || tas.getTransactionAttribute(method, targetClass) != null);
	}

	@Override
	public boolean equals(@Nullable Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TransactionAttributeSourcePointcut)) {
			return false;
		}
		TransactionAttributeSourcePointcut otherPc = (TransactionAttributeSourcePointcut) other;
		return ObjectUtils.nullSafeEquals(getTransactionAttributeSource(), otherPc.getTransactionAttributeSource());
	}

	@Override
	public int hashCode() {
		return TransactionAttributeSourcePointcut.class.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getName() + ": " + getTransactionAttributeSource();
	}


	/**
	 * Obtain the underlying TransactionAttributeSource (may be {@code null}).
	 * To be implemented by subclasses.
	 */
	@Nullable
	protected abstract TransactionAttributeSource getTransactionAttributeSource();


	/**
	 * {@link ClassFilter} that delegates to {@link TransactionAttributeSource#isCandidateClass}
	 * for filtering classes whose methods are not worth searching to begin with.
	 */
	private class TransactionAttributeSourceClassFilter implements ClassFilter {

		@Override
		public boolean matches(Class<?> clazz) {
			if (TransactionalProxy.class.isAssignableFrom(clazz) ||
					TransactionManager.class.isAssignableFrom(clazz) ||
					PersistenceExceptionTranslator.class.isAssignableFrom(clazz)) {
				return false;
			}
			TransactionAttributeSource tas = getTransactionAttributeSource();
			return (tas == null || tas.isCandidateClass(clazz));
		}
	}

}
