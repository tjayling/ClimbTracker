package com.qa.climbtracker.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.climbtracker.domain.dto.RouteDto;
import com.qa.climbtracker.domain.model.Route;
import com.qa.climbtracker.repo.RouteRepo;

@SpringBootTest
@ActiveProfiles("test")
public class RouteServiceTests {
	@Autowired
	private RouteService service;

	@MockBean
	private RouteRepo mockRepo;

	List<Route> expectedModels;
	List<RouteDto> expectedDtos;
	Route expectedModel;
	RouteDto expectedDto;
	
	@BeforeEach
	void setup() {
		expectedModels = List.of(new Route(), new Route());
		expectedDtos = List.of(new RouteDto(), new RouteDto());
		expectedModel = new Route();
		expectedDto = new RouteDto();
	}
	
	@Test
	void readAllTest() {
		Mockito.when(this.mockRepo.findAll()).thenReturn(expectedModels);

		assertThat(this.service.readAll()).isEqualTo(expectedDtos);

		Mockito.verify(this.mockRepo, Mockito.times(1)).findAll();
	}

	@Test
	void createTest() {
		Mockito.when(this.mockRepo.save(new Route())).thenReturn(expectedModel);

		assertThat(this.service.create(new Route())).isEqualTo(expectedDto);

		Mockito.verify(this.mockRepo, Mockito.times(1)).save(new Route());
	}

	@Test
	void createManyTest() {
		Mockito.when(this.mockRepo.saveAll(expectedModels)).thenReturn(expectedModels);

		assertThat(this.service.createMany(expectedModels)).isEqualTo(expectedDtos);

		Mockito.verify(this.mockRepo, Mockito.times(1)).saveAll(expectedModels);
	}

	@Test
	void updateTest() {
		Route before = new Route();
		before.setId(1L);
		before.setName("abc");

		Route after = new Route();
		after.setId(1L);
		after.setName("cba");

		RouteDto afterDto = new RouteDto();
		afterDto.setId(1L);
		afterDto.setName("cba");

		Mockito.when(this.mockRepo.findById(1L)).thenReturn(Optional.of(before));
		Mockito.when(this.mockRepo.save(before)).thenReturn(before);

		assertThat(this.service.update(after)).isEqualTo(afterDto);

		Mockito.verify(this.mockRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(before);
	}

	@Test
	void successfulDeleteTest() {
		Mockito.when(this.mockRepo.exists(List.of(1L, 2L))).thenReturn(List.of());

		assertThat(this.service.delete(List.of(1L, 2L))).isEqualTo(true);

		Mockito.verify(this.mockRepo, Mockito.times(1)).delete(List.of(1L, 2L));
	}

	@Test
	void unuccessfulDeleteTest() {
		Mockito.when(this.mockRepo.exists(List.of(1L, 2L))).thenReturn(List.of(expectedModel));

		assertThat(this.service.delete(List.of(1L, 2L))).isEqualTo(false);

		Mockito.verify(this.mockRepo, Mockito.times(1)).delete(List.of(1L, 2L));
	}

	@Test
	void readIdTest() {
		expectedModel.setId(1L);
		expectedDto.setId(1L);

		Mockito.when(this.mockRepo.readId(1L)).thenReturn(List.of(expectedModel));

		assertThat(this.service.readId(1L)).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readId(1L);
	}

	@Test
	void readNameTest() {
		expectedModel.setName("abc");
		expectedDto.setName("abc");

		Mockito.when(this.mockRepo.readName("abc")).thenReturn(List.of(expectedModel));

		assertThat(this.service.readName("abc")).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readName("abc");
	}

	@Test
	void readGradeTest() {
		expectedModel.setGrade("v1");
		expectedDto.setGrade("v1");

		Mockito.when(this.mockRepo.readGrade("v1")).thenReturn(List.of(expectedModel));

		assertThat(this.service.readGrade("v1")).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readGrade("v1");
	}
}
