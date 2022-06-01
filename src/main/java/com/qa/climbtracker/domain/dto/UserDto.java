package com.qa.climbtracker.domain.dto;

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
}

// id
// first name
// last name
// username
// password