package com.qa.climbtracker.domain.dto;

import com.qa.climbtracker.domain.model.Route;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClimbDto {
	private Long id;
	private Route route;
	private Integer timeTaken;
	private Integer attempts;
}

// id
// user id
// route id
// time taken
// completed climb