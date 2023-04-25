package com.app.api.exceptiontest.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.exceptiontest.dto.BindExceptionTestDTO;
import com.app.api.exceptiontest.dto.TestEnum;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exception")
@RequiredArgsConstructor
public class ExceptionTestController {

	@GetMapping("/bind-exception-test")
	public String bindExceptionTest(@Valid BindExceptionTestDTO bindExceptionTestDTO) {
		return "ok";
	}

	@GetMapping("/type-exception-test")
	public String typeMismatchException(TestEnum testEnum) {
		return "ok";
	}

	@GetMapping("/business-exception-test")
	public String businessExceptionTest(String isError) {
		if ("true".equals(isError)) {
			throw new BusinessException(ErrorCode.TEST);
		}
		return "ok";
	}

	@GetMapping("/exception-test")
	public String exceptionTest(String isError) {
		if ("true".equals(isError)) {
			throw new IllegalArgumentException("예외 테스트");
		}
		return "ok";
	}
}
