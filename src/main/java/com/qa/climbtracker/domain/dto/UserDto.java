package com.qa.climbtracker.domain.dto;

import java.util.List;

import com.qa.climbtracker.domain.model.Climb;

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
	private List<Climb> climbs;
}

// id
// first name
// last name
// username
// password