package com.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// @EnableAspectJAutoProxy 注解注册了 AnnotationAwareAspectJAutoProxyCreator 这个 beanPostProcessor。

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.aop")
public class AppConfig {

}
