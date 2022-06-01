package com.qa.climbtracker.service;

import java.util.Date;
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
		return this.mapToDto(this.repo.saveAll(climbs).stream().map(this::mapToDto).collect(Collectors.toList()));
	}

	public List<ClimbDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public ClimbDto update(ClimbDao newClimb) {
		ClimbDao climb = this.repo.findById(newClimb.getId()).get();
		climb.setUserId(newClimb.getUserId());
		climb.setRouteId(newClimb.getRouteId());
		climb.setTimeTaken(newClimb.getTimeTaken());
		climb.setDateClimbed(newClimb.getDateClimbed());
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
	
	public List<ClimbDto> readRouteId(Long routeId) {
		return this.repo.readUserId(routeId).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<ClimbDto> readDate(Long userId, Date date) {
		return this.repo.readDate(userId, date).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<ClimbDto> readCompleted(Long userId, Boolean completed) {
		return this.repo.readCompleted(userId, completed).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}

