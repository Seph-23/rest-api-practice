package com.cafein.backend.global.resolver.memberinfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafein.backend.domain.member.constant.Role;
import com.cafein.backend.global.jwt.service.TokenManager;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberInfoArgumentResolver implements HandlerMethodArgumentResolver {

	private final TokenManager tokenManager;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasMemberInfoAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
		boolean hasMemberInfoDTO = MemberInfoDTO.class.isAssignableFrom(parameter.getParameterType());
		return hasMemberInfoAnnotation && hasMemberInfoDTO;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		String authorizationHeader = request.getHeader("Authorization");
		String token = authorizationHeader.split(" ")[1];

		Claims tokenClaims = tokenManager.getTokenClaims(token);
		Long memberId = Long.valueOf((Integer) tokenClaims.get("memberId"));
		String role = (String)tokenClaims.get("role");

		return MemberInfoDTO.builder()
			.memberId(memberId)
			.role(Role.from(role))
			.build();
	}
}
