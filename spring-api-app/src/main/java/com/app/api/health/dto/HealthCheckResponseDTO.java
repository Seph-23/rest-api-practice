package com.app.api.health.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class HealthCheckResponseDTO {

	private String health;
	private List<String> activeProfiles;
}
