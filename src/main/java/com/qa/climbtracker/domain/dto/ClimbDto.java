package com.qa.climbtracker.domain.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClimbDto {
	private Long id;
	private String userId;
	private String routeId;
	private Integer timeTaken;
	private Date dateClimbed;
	private Boolean completedClimb;
}

// id
// user id
// route id
// time taken
// data climbed
// completed climb