package com.qa.climbtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.climbtracker.domain.model.Route;
import com.qa.climbtracker.domain.dto.RouteDto;
import com.qa.climbtracker.service.RouteService;

@RestController()
@RequestMapping("/route")
@CrossOrigin

public class RouteController {

	private RouteService service;

	@Autowired
	public RouteController(RouteService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<RouteDto> create(@RequestBody Route route) {
		return new ResponseEntity<>(this.service.create(route), HttpStatus.CREATED);
	}

	@PostMapping("/createMany")
	public ResponseEntity<List<RouteDto>> createMany(@RequestBody List<Route> routes) {
		return new ResponseEntity<>(this.service.createMany(routes), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<RouteDto>> readAll() {
		return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<RouteDto> update(@RequestBody Route route) {
		return new ResponseEntity<>(this.service.update(route), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{ids}")
	public ResponseEntity<Boolean> delete(@PathVariable List<Long> ids) {
		return this.service.delete(ids) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/readBy/id:{id}")
	public ResponseEntity<List<RouteDto>> readId(@PathVariable Long id) {
		List<RouteDto> routes = this.service.readId(id);
		return new ResponseEntity<>(routes, HttpStatus.OK);
	}

	@GetMapping("/readBy/name:{name}")
	public ResponseEntity<List<RouteDto>> readName(@PathVariable String name) {
		List<RouteDto> routes = this.service.readName(name);
		return new ResponseEntity<>(routes, HttpStatus.OK);
	}

	@GetMapping("/readBy/grade:{grade}")
	public ResponseEntity<List<RouteDto>> readGrade(@PathVariable String grade) {
		List<RouteDto> routes = this.service.readGrade(grade);
		return new ResponseEntity<>(routes, HttpStatus.OK);
	}

	// id
	// grade
}
