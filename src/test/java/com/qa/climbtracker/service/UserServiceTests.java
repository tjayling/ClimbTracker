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

import com.qa.climbtracker.domain.dto.UserDto;
import com.qa.climbtracker.domain.model.User;
import com.qa.climbtracker.repo.UserRepo;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepo mockRepo;

	List<User> expectedModels;
	List<UserDto> expectedDtos;
	User expectedModel;
	UserDto expectedDto;

	@BeforeEach
	void setup() {
		expectedModels = List.of(new User(), new User());
		expectedDtos = List.of(new UserDto(), new UserDto());
		expectedModel = new User();
		expectedDto = new UserDto();
	}

	@Test
	void readAllTest() {
		Mockito.when(this.mockRepo.findAll()).thenReturn(expectedModels);

		assertThat(this.service.readAll()).isEqualTo(expectedDtos);

		Mockito.verify(this.mockRepo, Mockito.times(1)).findAll();
	}

	@Test
	void createTest() {
		Mockito.when(this.mockRepo.save(new User())).thenReturn(expectedModel);

		assertThat(this.service.create(new User())).isEqualTo(expectedDto);

		Mockito.verify(this.mockRepo, Mockito.times(1)).save(new User());
	}

	@Test
	void createManyTest() {
		Mockito.when(this.mockRepo.saveAll(expectedModels)).thenReturn(expectedModels);

		assertThat(this.service.createMany(expectedModels)).isEqualTo(expectedDtos);

		Mockito.verify(this.mockRepo, Mockito.times(1)).saveAll(expectedModels);
	}

	@Test
	void updateTest() {
		User before = new User();
		before.setId(1L);
		before.setFirstName("Tom");

		User after = new User();
		after.setId(1L);
		after.setFirstName("Joe");

		UserDto afterDto = new UserDto();
		afterDto.setId(1L);
		afterDto.setFirstName("Joe");

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
	void readUsername() {
		String username = "USERNAME";
		User user = new User();
		user.setId(1L);
		expectedModel.setId(1L);
		expectedModel.setUsername(username);
		expectedDto.setId(1L);
		expectedDto.setUsername(username);

		Mockito.when(this.mockRepo.readUsername(username)).thenReturn(List.of(expectedModel));

		assertThat(this.service.readUsername(username)).isEqualTo(List.of(expectedDto));

		Mockito.verify(this.mockRepo, Mockito.times(1)).readUsername(username);
	}
}
