package com.cafein.backend.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cafein.backend.domain.member.constant.Role;
import com.cafein.backend.global.error.ErrorCode;
import com.cafein.backend.global.error.exception.AuthenticationException;
import com.cafein.backend.global.jwt.service.TokenManager;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

	private final TokenManager tokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		String authorizationHeader = request.getHeader("Authorization");
		String accessToken = authorizationHeader.split(" ")[1];

		Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
		String role = (String)tokenClaims.get("role");
		if (!Role.ADMIN.equals(Role.valueOf(role))) {
			throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);
		}
		return true;
	}
}
