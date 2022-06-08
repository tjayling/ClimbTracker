package com.qa.climbtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.climbtracker.domain.model.Route;
import com.qa.climbtracker.domain.dto.RouteDto;
import com.qa.climbtracker.repo.RouteRepo;

@Service
public class RouteService {
	private ModelMapper mapper;
	RouteRepo repo;

	public RouteService(RouteRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private RouteDto mapToDto(Route route) {
		return this.mapper.map(route, RouteDto.class);
	}
	
	public RouteDto create(Route route) {
		return this.mapToDto(this.repo.save(route));
	}
	
	public List<RouteDto> createMany(List<Route> routes) {
		return this.repo.saveAll(routes).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public List<RouteDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public RouteDto update(Route newRoute) {
		Route route = this.repo.findById(newRoute.getId()).get();
		route.setGrade(null);
		return this.mapToDto(this.repo.save(route));
	}
	
	@Transactional
	public Boolean delete(List<Long> ids) {
		this.repo.delete(ids);
		return this.repo.exists(ids).size() == 0 ? true : false;
	}
	
	public List<RouteDto> readId(Long id) {
		return this.repo.readId(id).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	public List<RouteDto> readName(String name) {
		return this.repo.readName(name).stream().map(this::mapToDto).collect(Collectors.toList());	}
	
	public List<RouteDto> readGrade(String grade) {
		return this.repo.readGrade(grade).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}

