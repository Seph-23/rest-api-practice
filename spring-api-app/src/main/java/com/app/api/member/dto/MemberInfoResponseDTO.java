package com.app.api.member.dto;

import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberInfoResponseDTO {

	private Long memberId;
	private String email;
	private String memberName;
	private String profile;
	private Role role;

	public static MemberInfoResponseDTO of(final Member member) {
		return MemberInfoResponseDTO.builder()
			.memberId(member.getMemberId())
			.memberName(member.getMemberName())
			.email(member.getEmail())
			.profile(member.getProfile())
			.role(member.getRole())
			.build();
	}
}
