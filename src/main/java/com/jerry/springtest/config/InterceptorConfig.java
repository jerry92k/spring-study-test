package com.jerry.springtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jerry.springtest.interceptor.AInterceptor;
import com.jerry.springtest.interceptor.BInterceptor;
import com.jerry.springtest.interceptor.CacheLoggingInterceptor;
import com.jerry.springtest.interceptor.LoggingInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new AInterceptor())
			.order(2) // 적용되는 인터페이스가 여러개일 때 순서 명시
			.addPathPatterns("/interceptor/test");

		registry.addInterceptor(new BInterceptor())
			.order(1)
			.addPathPatterns("/interceptor/test");

		registry.addInterceptor(new LoggingInterceptor())
			.addPathPatterns("/markets/v1");

		registry.addInterceptor(new CacheLoggingInterceptor())
			.addPathPatterns("/markets/v2");


	}
}
