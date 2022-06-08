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

import com.qa.climbtracker.domain.dto.ClimbDto;
import com.qa.climbtracker.domain.model.Climb;
import com.qa.climbtracker.service.ClimbService;

@SpringBootTest
@ActiveProfiles("test")
public class ClimbControllerTests {
	@Autowired
	private ClimbController controller;

	@MockBean
	private ClimbService mockService;

	List<ClimbDto> expectedDtos;

	@BeforeEach
	void setup() {
	}

	@Test
	void readAllTest() {
		List<ClimbDto> expectedDtos = List.of(new ClimbDto());
		ResponseEntity<List<ClimbDto>> expectedResponseEntity = new ResponseEntity<>(List.of(new ClimbDto()),
				HttpStatus.OK);

		Mockito.when(this.mockService.readAll()).thenReturn(expectedDtos);

		assertThat(this.controller.readAll()).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).readAll();
	}

	@Test
	void createTest() {
		Climb climb = new Climb();
		ClimbDto expectedDto = new ClimbDto();
		ResponseEntity<ClimbDto> expectedResponseEntity = new ResponseEntity<>(new ClimbDto(), HttpStatus.CREATED);

		Mockito.when(this.mockService.create(climb)).thenReturn(expectedDto);

		assertThat(this.controller.create(climb)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).create(climb);
	}

	@Test
	void createManyTest() {
		List<Climb> climbs = List.of(new Climb());
		List<ClimbDto> expectedDtos = List.of(new ClimbDto());
		ResponseEntity<List<ClimbDto>> expectedResponseEntity = new ResponseEntity<>(List.of(new ClimbDto()),
				HttpStatus.CREATED);

		Mockito.when(this.mockService.createMany(climbs)).thenReturn(expectedDtos);

		assertThat(this.controller.createMany(climbs)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).createMany(climbs);
	}

	@Test
	void updateTest() {
		Climb climb = new Climb();
		ClimbDto expectedDto = new ClimbDto();
		ResponseEntity<ClimbDto> expectedResponseEntity = new ResponseEntity<>(new ClimbDto(), HttpStatus.ACCEPTED);

		Mockito.when(this.mockService.update(climb)).thenReturn(expectedDto);

		assertThat(this.controller.update(climb)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).update(climb);
	}

	@Test
	void successfulDeleteTest() {
		Long id = 1L;
		ResponseEntity<ClimbDto> expectedResponseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		Mockito.when(this.mockService.delete(List.of(id))).thenReturn(true);

		assertThat(this.controller.delete(List.of(id))).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).delete(List.of(id));
	}

	@Test
	void unsuccessfulDeleteTest() {
		Long id = 1L;
		ResponseEntity<ClimbDto> expectedResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Mockito.when(this.mockService.delete(List.of(id))).thenReturn(false);

		assertThat(this.controller.delete(List.of(id))).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).delete(List.of(id));
	}

	@Test
	void readIdTest() {
		Long id = 1L;
		List<ClimbDto> expectedDtos = List.of(new ClimbDto());
		ResponseEntity<List<ClimbDto>> expectedResponseEntity = new ResponseEntity<>(List.of(new ClimbDto()),
				HttpStatus.OK);

		Mockito.when(this.mockService.readId(id)).thenReturn(expectedDtos);

		assertThat(this.controller.readId(id)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).readId(id);
	}
	
	@Test
	void readUserIdTest() {
		Long id = 1L;
		List<ClimbDto> expectedDtos = List.of(new ClimbDto());
		ResponseEntity<List<ClimbDto>> expectedResponseEntity = new ResponseEntity<>(List.of(new ClimbDto()),
				HttpStatus.OK);
		
		Mockito.when(this.mockService.readUserId(id)).thenReturn(expectedDtos);
		
		assertThat(this.controller.readUserId(id)).isEqualTo(expectedResponseEntity);
		
		Mockito.verify(this.mockService, Mockito.times(1)).readUserId(id);
	}
	
	@Test
	void readRouteIdTest() {
		Long userId = 1L;
		Long routeId = 1L;
		List<ClimbDto> expectedDtos = List.of(new ClimbDto());
		ResponseEntity<List<ClimbDto>> expectedResponseEntity = new ResponseEntity<>(List.of(new ClimbDto()),
				HttpStatus.OK);
		
		Mockito.when(this.mockService.readRouteId(userId, routeId)).thenReturn(expectedDtos);
		
		assertThat(this.controller.readRouteId(userId, routeId)).isEqualTo(expectedResponseEntity);
		
		Mockito.verify(this.mockService, Mockito.times(1)).readRouteId(userId, routeId);
	}
	
	@Test
	void readAttemptsTest() {
		Long userId = 1L;
		Integer attempts = 10;
		List<ClimbDto> expectedDtos = List.of(new ClimbDto());
		ResponseEntity<List<ClimbDto>> expectedResponseEntity = new ResponseEntity<>(List.of(new ClimbDto()),
				HttpStatus.OK);
		
		Mockito.when(this.mockService.readAttempts(userId, attempts)).thenReturn(expectedDtos);
		
		assertThat(this.controller.readAttempts(userId, attempts)).isEqualTo(expectedResponseEntity);
		
		Mockito.verify(this.mockService, Mockito.times(1)).readAttempts(userId, attempts);
	}
}
