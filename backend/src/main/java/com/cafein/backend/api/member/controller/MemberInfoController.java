package com.cafein.backend.api.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafein.backend.api.member.dto.MemberInfoResponseDTO;
import com.cafein.backend.api.member.service.MemberInfoService;
import com.cafein.backend.global.jwt.service.TokenManager;
import com.cafein.backend.global.resolver.memberinfo.MemberInfo;
import com.cafein.backend.global.resolver.memberinfo.MemberInfoDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberInfoController {

	private final MemberInfoService memberInfoService;
	private final TokenManager tokenManager;

	@GetMapping("/info")
	public ResponseEntity<MemberInfoResponseDTO> getMemberInfo(@MemberInfo MemberInfoDTO memberInfoDTO) {

		Long memberId = memberInfoDTO.getMemberId();
		MemberInfoResponseDTO memberInfoResponseDTO = memberInfoService.getMemberInfo(memberId);
		return ResponseEntity.ok(memberInfoResponseDTO);
	}
}
