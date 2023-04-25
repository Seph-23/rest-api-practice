package com.app.web.kakaotoken.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.app.web.kakaotoken.dto.KakaoTokenDTO;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenclient")
public interface KakaoTokenClient {

	@PostMapping(value = "/oauth/token", consumes = "application/json")
	KakaoTokenDTO.Response requestKakaoToken(@RequestHeader("Content-Type") String contentType,
											 @SpringQueryMap KakaoTokenDTO.Request request);
}
