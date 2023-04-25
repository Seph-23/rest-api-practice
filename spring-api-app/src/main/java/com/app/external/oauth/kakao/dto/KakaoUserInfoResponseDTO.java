package com.app.external.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class KakaoUserInfoResponseDTO {

	private String id;

	@JsonProperty("kakao_account")
	private KakaoAccount kakaoAccount;

	@Getter @Setter
	@AllArgsConstructor
	@RequiredArgsConstructor
	public static class KakaoAccount {
		private String email;
		private Profile profile;

		@Getter @Setter
		@AllArgsConstructor
		@RequiredArgsConstructor
		public static class Profile {

			private String nickname;

			@JsonProperty("thumbnail_image_url")
			private String thumbnailImageUrl;
		}
	}
}
