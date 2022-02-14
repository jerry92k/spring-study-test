package com.jerry.springtest.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jerry.springtest.resolver.MarketPrincipalArgumentResolver;
import com.jerry.springtest.resolver.MarketPrincipalArgumentResolverV2;

@Configuration
public class ArgumentResolverConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		// 먼저 등록한게 실행됨
		resolvers.add(createMarketPrincipalArgumentResolverV2());
		resolvers.add(createMarketPrincipalArgumentResolver());
	}

	private MarketPrincipalArgumentResolver createMarketPrincipalArgumentResolver() {
		return new MarketPrincipalArgumentResolver();
	}

	private MarketPrincipalArgumentResolverV2 createMarketPrincipalArgumentResolverV2() {
		return new MarketPrincipalArgumentResolverV2();
	}
}
