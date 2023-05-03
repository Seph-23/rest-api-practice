package com.cafein.backend.global.config.web;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafein.backend.global.interceptor.AdminAuthorizationInterceptor;
import com.cafein.backend.global.interceptor.AuthenticationInterceptor;
import com.cafein.backend.global.resolver.memberinfo.MemberInfoArgumentResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final AuthenticationInterceptor authenticationInterceptor;
	private final MemberInfoArgumentResolver memberInfoArgumentResolver;
	private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;

	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("http://localhost:8082")
			.allowedMethods(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.PATCH.name(),
				HttpMethod.DELETE.name(),
				HttpMethod.OPTIONS.name()
			)
			.maxAge(3600);
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
			.order(1)
			.addPathPatterns("/api/**")
			.excludePathPatterns("/api/oauth/login",
				"/api/access-token/issue",
				"/api/logout",
				"/api/health");

		registry.addInterceptor(adminAuthorizationInterceptor)
			.order(2)
			.addPathPatterns("/api/admin/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(memberInfoArgumentResolver);
	}
}
