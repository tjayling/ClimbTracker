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

import com.qa.climbtracker.domain.dto.ClimbDto;
import com.qa.climbtracker.domain.model.Climb;
import com.qa.climbtracker.domain.model.Route;
import com.qa.climbtracker.domain.model.User;
import com.qa.climbtracker.repo.ClimbRepo;

@SpringBootTest
@ActiveProfiles("test")
public class ClimbServiceTests {
	@Autowired
	private ClimbService service;

	@MockBean
	private ClimbRepo mockRepo;

	List<Climb> expectedModels;
	List<ClimbDto> expectedDtos;
	Climb expectedModel;
	ClimbDto expectedDto;

	@BeforeEach
	void setup() {
		expectedModels = List.of(new Climb(), new Climb());
		expectedDtos = List.of(new ClimbDto(), new ClimbDto());
		expectedModel = new Climb();
		expectedDto = new ClimbDto();
	}

	@Test
	void readAllTest() {
		Mockito.when(this.mockRepo.findAll()).thenReturn(expectedModels);

		assertThat(this.service.readAll()).isEqualTo(expectedDtos);

		Mockito.verify(this.mockRepo, Mockito.times(1)).findAll();
	}

	@Test
	void createTest() {
		Mockito.when(this.mockRepo.save(new Climb())).thenReturn(expectedModel);

		assertThat(this.service.create(new Climb())).isEqualTo(expectedDto);

		Mockito.verify(this.mockRepo, Mockito.times(1)).save(new Climb());
	}

	@Test
	void createManyTest() {
		Mockito.when(this.mockRepo.saveAll(expectedModels)).thenReturn(expectedModels);

		assertThat(this.service.createMany(expectedModels)).isEqualTo(expectedDtos);

		Mockito.verify(this.mockRepo, Mockito.times(1)).saveAll(expectedModels);
	}

	@Test
	void updateTest() {
		Climb before = new Climb();
		before.setId(1L);
		before.setAttempts(10);

		Climb after = new Climb();
		after.setId(1L);
		after.setAttempts(5);

		ClimbDto afterDto = new ClimbDto();
		afterDto.setId(1L);
		afterDto.setAttempts(5);

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
	void readUserIdTest() {
		User user = new User();
		user.setId(1L);
		expectedModel.setId(1L);
		expectedModel.setUser(user);
		expectedDto.setId(1L);

		Mockito.when(this.mockRepo.readUserId(1L)).thenReturn(List.of(expectedModel));

		assertThat(this.service.readUserId(1L)).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readUserId(1L);
	}

	@Test
	void readRouteIdTest() {
		User user = new User();
		user.setId(1L);
		Route route = new Route();
		route.setId(1L);
		expectedModel.setId(1L);
		expectedModel.setUser(user);
		expectedModel.setRoute(route);
		expectedDto.setId(1L);
		expectedDto.setRoute(route);

		Mockito.when(this.mockRepo.readRouteId(1L, 1L)).thenReturn(List.of(expectedModel));

		assertThat(this.service.readRouteId(1L, 1L)).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readRouteId(1L, 1L);
	}

	@Test
	void readAttempts() {
		User user = new User();
		user.setId(1L);
		Route route = new Route();
		route.setId(1L);
		expectedModel.setId(1L);
		expectedModel.setUser(user);
		expectedModel.setAttempts(5);
		expectedDto.setId(1L);
		expectedDto.setAttempts(5);

		Mockito.when(this.mockRepo.readAttempts(1L, 5)).thenReturn(List.of(expectedModel));

		assertThat(this.service.readAttempts(1L, 5)).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readAttempts(1L, 5);
	}
}
