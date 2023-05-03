package com.cafein.backend.global.resolver.memberinfo;

import com.cafein.backend.domain.member.constant.Role;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberInfoDTO {

	private Long memberId;
	private Role role;
}
