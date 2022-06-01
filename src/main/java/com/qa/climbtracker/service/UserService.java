package com.qa.climbtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.climbtracker.domain.dao.UserDao;
import com.qa.climbtracker.domain.dto.UserDto;
import com.qa.climbtracker.repo.UserRepo;

@Service
public class UserService {
	private ModelMapper mapper;
	UserRepo repo;

	public UserService(UserRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private UserDto mapToDto(UserDao user) {
		return this.mapper.map(user, UserDto.class);
	}
	
	public UserDto create(UserDao user) {
		return this.mapToDto(this.repo.save(user));
	}
	
	public List<UserDto> createMany(List<UserDao> users) {
		return this.repo.saveAll(users).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<UserDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public UserDto update(UserDao newUser) {
		UserDao user = this.repo.findById(newUser.getId()).get();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setUsername(newUser.getUsername());
		user.setPassword(newUser.getPassword());
		return this.mapToDto(this.repo.save(user));
	}
	
	@Transactional
	public Boolean delete(List<Long> ids) {
		this.repo.delete(ids);
		return this.repo.exists(ids).size() == 0 ? true : false;
	}
	
	public List<UserDto> readId(Long id) {
		return this.repo.readId(id).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<UserDto> readUsername(String username) {
		return this.repo.readUsername(username).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}

