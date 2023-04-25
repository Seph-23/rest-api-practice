package com.app.api.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.login.dto.OAuthLoginDTO;
import com.app.api.login.service.OAuthLoginService;
import com.app.api.login.validator.OAuthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OAuthLoginController {

	private final OAuthValidator oAuthValidator;
	private final OAuthLoginService oAuthLoginService;

	@PostMapping("/login")
	public ResponseEntity<OAuthLoginDTO.Response> oauthLogin(@RequestBody OAuthLoginDTO.Request oauthLoginRequestDTO,
		HttpServletRequest httpServletRequest) {

		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
		oAuthValidator.validateMemberType(oauthLoginRequestDTO.getMemberType());

		String accessToken = authorizationHeader.split(" ")[1];
		OAuthLoginDTO.Response jwtTokenResponseDTO = oAuthLoginService
			.oauthLogin(accessToken, MemberType.from(oauthLoginRequestDTO.getMemberType()));

		return ResponseEntity.ok(jwtTokenResponseDTO);
	}
}
