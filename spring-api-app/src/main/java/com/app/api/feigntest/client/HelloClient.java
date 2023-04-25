package com.app.api.feigntest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.api.health.dto.HealthCheckResponseDTO;

@FeignClient(url = "http://localhost:8080", name = "helloClient")
public interface HelloClient {

	@GetMapping(value = "/api/health", consumes = "application/json")
	HealthCheckResponseDTO healthCheck();
}
