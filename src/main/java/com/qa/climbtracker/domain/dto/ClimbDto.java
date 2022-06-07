package com.qa.climbtracker.domain.dto;

import com.qa.climbtracker.domain.dao.RouteDao;
import com.qa.climbtracker.domain.dao.UserDao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClimbDto {
	private Long id;
	private RouteDao route;
	private Integer timeTaken;
	private Integer attempts;
}

// id
// user id
// route id
// time taken
// completed climb