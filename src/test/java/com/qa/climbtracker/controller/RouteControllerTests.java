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

import com.qa.climbtracker.domain.dto.RouteDto;
import com.qa.climbtracker.domain.model.Route;
import com.qa.climbtracker.service.RouteService;

@SpringBootTest
@ActiveProfiles("test")
public class RouteControllerTests {
	@Autowired
	private RouteController controller;

	@MockBean
	private RouteService mockService;

	List<RouteDto> expectedDtos;

	@BeforeEach
	void setup() {
	}

	@Test
	void readAllTest() {
		List<RouteDto> expectedDtos = List.of(new RouteDto());
		ResponseEntity<List<RouteDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);

		Mockito.when(this.mockService.readAll()).thenReturn(expectedDtos);

		assertThat(this.controller.readAll()).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).readAll();
	}

	@Test
	void createTest() {
		Route climb = new Route();
		RouteDto expectedDto = new RouteDto();
		ResponseEntity<RouteDto> expectedResponseEntity = new ResponseEntity<>(new RouteDto(), HttpStatus.CREATED);

		Mockito.when(this.mockService.create(climb)).thenReturn(expectedDto);

		assertThat(this.controller.create(climb)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).create(climb);
	}

	@Test
	void createManyTest() {
		List<Route> climbs = List.of(new Route());
		List<RouteDto> expectedDtos = List.of(new RouteDto());
		ResponseEntity<List<RouteDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.CREATED);

		Mockito.when(this.mockService.createMany(climbs)).thenReturn(expectedDtos);

		assertThat(this.controller.createMany(climbs)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).createMany(climbs);
	}

	@Test
	void updateTest() {
		Route climb = new Route();
		RouteDto expectedDto = new RouteDto();
		ResponseEntity<RouteDto> expectedResponseEntity = new ResponseEntity<>(new RouteDto(), HttpStatus.ACCEPTED);

		Mockito.when(this.mockService.update(climb)).thenReturn(expectedDto);

		assertThat(this.controller.update(climb)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).update(climb);
	}

	@Test
	void successfulDeleteTest() {
		Long id = 1L;
		ResponseEntity<RouteDto> expectedResponseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		Mockito.when(this.mockService.delete(List.of(id))).thenReturn(true);

		assertThat(this.controller.delete(List.of(id))).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).delete(List.of(id));
	}

	@Test
	void unsuccessfulDeleteTest() {
		Long id = 1L;
		ResponseEntity<RouteDto> expectedResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Mockito.when(this.mockService.delete(List.of(id))).thenReturn(false);

		assertThat(this.controller.delete(List.of(id))).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).delete(List.of(id));
	}

	@Test
	void readIdTest() {
		Long id = 1L;
		List<RouteDto> expectedDtos = List.of(new RouteDto());
		ResponseEntity<List<RouteDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);

		Mockito.when(this.mockService.readId(id)).thenReturn(expectedDtos);

		assertThat(this.controller.readId(id)).isEqualTo(expectedResponseEntity);

		Mockito.verify(this.mockService, Mockito.times(1)).readId(id);
	}
	
	@Test
	void readNameTest() {
		String name = "abc";
		List<RouteDto> expectedDtos = List.of(new RouteDto());
		ResponseEntity<List<RouteDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);
		
		Mockito.when(this.mockService.readName(name)).thenReturn(expectedDtos);
		
		assertThat(this.controller.readName(name)).isEqualTo(expectedResponseEntity);
		
		Mockito.verify(this.mockService, Mockito.times(1)).readName(name);
	}
	
	@Test
	void readGradeTest() {
		String grade = "v1";
		List<RouteDto> expectedDtos = List.of(new RouteDto());
		ResponseEntity<List<RouteDto>> expectedResponseEntity = new ResponseEntity<>(expectedDtos,
				HttpStatus.OK);
		
		Mockito.when(this.mockService.readGrade(grade)).thenReturn(expectedDtos);
		
		assertThat(this.controller.readGrade(grade)).isEqualTo(expectedResponseEntity);
		
		Mockito.verify(this.mockService, Mockito.times(1)).readGrade(grade);
	}
}
