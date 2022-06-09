package com.qa.climbtracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.climbtracker.domain.dto.UserDto;
import com.qa.climbtracker.domain.model.User;
import com.qa.climbtracker.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTests {
	@Autowired
	private UserController controller;

	@MockBean
	private UserService mockService;

	List<UserDto> expectedDtos;

	@BeforeEach
	void setup() {
	}

	@Test
	void readAllTest() {
		List<UserDto> expectedDtos = List.of(new UserDto());
		ResponseEntity<List<UserDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);

		Mockito.when(this.mockService.readAll()).thenReturn(expectedDtos);

		assertThat(this.controller.readAll()).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).readAll();
	}

	@Test
	void createTest() {
		User climb = new User();
		UserDto expectedDto = new UserDto();
		ResponseEntity<UserDto> expectedResponseEntity = new ResponseEntity<>(new UserDto(), HttpStatus.CREATED);

		Mockito.when(this.mockService.create(climb)).thenReturn(expectedDto);

		assertThat(this.controller.create(climb)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).create(climb);
	}

	@Test
	void createManyTest() {
		List<User> climbs = List.of(new User());
		List<UserDto> expectedDtos = List.of(new UserDto());
		ResponseEntity<List<UserDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.CREATED);

		Mockito.when(this.mockService.createMany(climbs)).thenReturn(expectedDtos);

		assertThat(this.controller.createMany(climbs)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).createMany(climbs);
	}

	@Test
	void updateTest() {
		User climb = new User();
		UserDto expectedDto = new UserDto();
		ResponseEntity<UserDto> expectedResponseEntity = new ResponseEntity<>(new UserDto(), HttpStatus.ACCEPTED);

		Mockito.when(this.mockService.update(climb)).thenReturn(expectedDto);

		assertThat(this.controller.update(climb)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).update(climb);
	}

	@Test
	void successfulDeleteTest() {
		Long id = 1L;
		ResponseEntity<UserDto> expectedResponseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		Mockito.when(this.mockService.delete(List.of(id))).thenReturn(true);

		assertThat(this.controller.delete(List.of(id))).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).delete(List.of(id));
	}

	@Test
	void unsuccessfulDeleteTest() {
		Long id = 1L;
		ResponseEntity<UserDto> expectedResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Mockito.when(this.mockService.delete(List.of(id))).thenReturn(false);

		assertThat(this.controller.delete(List.of(id))).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).delete(List.of(id));
	}

	@Test
	void readIdTest() {
		Long id = 1L;
		List<UserDto> expectedDtos = List.of(new UserDto());
		ResponseEntity<List<UserDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);

		Mockito.when(this.mockService.readId(id)).thenReturn(expectedDtos);

		assertThat(this.controller.readId(id)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).readId(id);
	}
	
	@Test
	void readUsernameTest() {
		String username = "abc";
		List<UserDto> expectedDtos = List.of(new UserDto());
		ResponseEntity<List<UserDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);
		
		Mockito.when(this.mockService.readUsername(username)).thenReturn(expectedDtos);
		
		assertThat(this.controller.readUsername(username)).isEqualTo(expectedResponseEntity);
		
		Mockito.verify(this.mockService, Mockito.times(1)).readUsername(username);
	}
}
