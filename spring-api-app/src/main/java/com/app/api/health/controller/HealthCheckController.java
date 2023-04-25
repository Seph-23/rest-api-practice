package com.app.api.health.controller;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.health.dto.HealthCheckResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {

	private final Environment environment;

	@GetMapping("/health")
	public ResponseEntity<HealthCheckResponseDTO> healthCheck() {

		//Read Time Out 테스트용
		// try {
		// 	Thread.sleep(6000);
		// } catch (InterruptedException e) {
		// 	e.printStackTrace();
		// }

		HealthCheckResponseDTO healthCheckResponseDTO = HealthCheckResponseDTO.builder()
			.health("ok")
			.activeProfiles(Arrays.asList(environment.getActiveProfiles()))
			.build();
		return ResponseEntity.ok(healthCheckResponseDTO);
	}
}
