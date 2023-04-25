package com.app.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business exception test"),

	//인증
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
	NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다.")
	;

	private HttpStatus httpStatus;
	private String errorCode;
	private String message;

	ErrorCode(final HttpStatus httpStatus, final String errorCode, final String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}
}
