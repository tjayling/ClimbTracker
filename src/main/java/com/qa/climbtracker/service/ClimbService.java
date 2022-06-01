package com.qa.climbtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.climbtracker.domain.dao.ClimbDao;
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
	
	private ClimbDto mapToDto(ClimbDao climb) {
		return this.mapper.map(climb, ClimbDto.class);
	}
	
	public ClimbDto create(ClimbDao climb) {
		return this.mapToDto(this.repo.save(climb));
	}
	
	public List<ClimbDto> createMany(List<ClimbDao> climbs) {
		return this.repo.saveAll(climbs).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<ClimbDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public ClimbDto update(ClimbDao newClimb) {
		ClimbDao climb = this.repo.findById(newClimb.getId()).get();
		climb.setTimeTaken(newClimb.getTimeTaken());
		climb.setCompletedClimb(newClimb.getCompletedClimb());

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
	
	public List<ClimbDto> readCompleted(Long userId, Boolean completed) {
		return this.repo.readCompleted(userId, completed).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}

