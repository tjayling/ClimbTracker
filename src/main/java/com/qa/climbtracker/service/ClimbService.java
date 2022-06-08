package com.qa.climbtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.climbtracker.domain.model.Climb;
import com.qa.climbtracker.domain.dto.ClimbDto;
import com.qa.climbtracker.repo.ClimbRepo;

@Service
public class ClimbService {
	private ModelMapper mapper;
	ClimbRepo repo;

	public ClimbService(ClimbRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ClimbDto mapToDto(Climb climb) {
		return this.mapper.map(climb, ClimbDto.class);
	}
	
	public ClimbDto create(Climb climb) {
		return this.mapToDto(this.repo.save(climb));
	}
	
	public List<ClimbDto> createMany(List<Climb> climbs) {
		return this.repo.saveAll(climbs).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<ClimbDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public ClimbDto update(Climb newClimb) {
		Climb climb = this.repo.readId(newClimb.getId()).get(0);
		climb.setTimeTaken(newClimb.getTimeTaken());
		climb.setAttempts(newClimb.getAttempts());
		return this.mapToDto(this.repo.save(climb));
	}
	
	@Transactional
	public Boolean delete(List<Long> ids) {
		this.repo.delete(ids);
		return this.repo.exists(ids).size() == 0 ? true : false;
	}
	
	public List<ClimbDto> readId(Long id) {
		return this.repo.readId(id).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<ClimbDto> readUserId(Long userId) {
		return this.repo.readUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<ClimbDto> readRouteId(Long userId, Long routeId) {
		return this.repo.readRouteId(userId, routeId).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<ClimbDto> readAttempts(Long userId, Integer attempts) {
		return this.repo.readAttempts(userId, attempts).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}

