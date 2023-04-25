package com.app.api.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.login.dto.OAuthLoginDTO;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.external.oauth.model.OAuthAttributes;
import com.app.external.oauth.service.SocialLoginApiService;
import com.app.external.oauth.service.SocialLoginApiServiceFactory;
import com.app.global.jwt.dto.JwtTokenDTO;
import com.app.global.jwt.service.TokenManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OAuthLoginService {

	private final MemberService memberService;
	private final TokenManager tokenManager;

	public OAuthLoginDTO.Response oauthLogin(String accesstoken, MemberType memberType) {
		SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
		OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accesstoken);
		log.info("userInfo : {}", userInfo);

		JwtTokenDTO jwtTokenDTO;
		Optional<Member> optionalMember = memberService.findMemberByEmail(userInfo.getEmail());
		if (optionalMember.isEmpty()) {		//신규 회원가입
			Member oauthMember = userInfo.toMemberEntity(memberType, Role.ADMIN);
			oauthMember = memberService.registerMember(oauthMember);

			//토큰 생성
			jwtTokenDTO = tokenManager.createJwtTokenDto(oauthMember.getMemberId(), oauthMember.getRole());
			oauthMember.updateRefreshToken(jwtTokenDTO);
		} else {	//기존 회원
			Member oauthMember = optionalMember.get();

			//토큰 생성
			jwtTokenDTO = tokenManager.createJwtTokenDto(oauthMember.getMemberId(), oauthMember.getRole());
			oauthMember.updateRefreshToken(jwtTokenDTO);
		}

		return OAuthLoginDTO.Response.of(jwtTokenDTO);
	}
}
