package com.qa.climbtracker.domain.dto;

import java.util.List;

import com.qa.climbtracker.domain.model.Climb;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteDto {
	private Long id;
	private String name;
	private String grade;
	private List<Climb> climbs;
}

// id
// grade