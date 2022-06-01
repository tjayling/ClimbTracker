package com.qa.climbtracker.domain.dto;

import java.util.List;

import com.qa.climbtracker.domain.dao.ClimbDao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private Long id;
	private String firstName;
	private String lastName;
	public String username;
	public String password;
	private List<ClimbDao> climbs;
}

// id
// first name
// last name
// username
// password