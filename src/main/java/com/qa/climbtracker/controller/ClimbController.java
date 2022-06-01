package com.qa.climbtracker.controller;

import java.util.Date;
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

import com.qa.climbtracker.domain.dao.ClimbDao;
import com.qa.climbtracker.domain.dto.ClimbDto;
import com.qa.climbtracker.service.ClimbService;

@RestController()
@RequestMapping("/climb")
@CrossOrigin
public class ClimbController {
	
	private ClimbService service;
	
	@Autowired
	public ClimbController(ClimbService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ClimbDto> create(@RequestBody ClimbDao climb) {
		return new ResponseEntity<>(this.service.create(climb), HttpStatus.CREATED);
	}
	
	@PostMapping("/createMany")
	public ResponseEntity<List<ClimbDto>> createMany(@RequestBody List<ClimbDao> climbs) {
		return new ResponseEntity<>(this.service.createMany(climbs), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<ClimbDto>> readAll() {
		return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ClimbDto> update(@RequestBody ClimbDao climb) {
		return new ResponseEntity<>(this.service.update(climb), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{ids}")
	public ResponseEntity<Boolean> delete(@PathVariable List<Long> ids) {
		return this.service.delete(ids) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/readBy/id:{id}")
	public ResponseEntity<List<ClimbDto>> readId(@PathVariable Long id) {
		List<ClimbDto> climbs = this.service.readId(id);
		return new ResponseEntity<>(climbs, HttpStatus.OK);
	}

	@GetMapping("/readBy/user_id:{userId}")
	public ResponseEntity<List<ClimbDto>> readUserId(@PathVariable Long userId) {
		List<ClimbDto> climbs = this.service.readUserId(userId);
		return new ResponseEntity<>(climbs, HttpStatus.OK);
	}

	@GetMapping("/readBy/user_id:{userId}/route_id:{routeId}")
	public ResponseEntity<List<ClimbDto>> readRouteId(@PathVariable Long userId, @PathVariable Long routeId) {
		List<ClimbDto> climbs = this.service.readRouteId(userId, routeId);
		return new ResponseEntity<>(climbs, HttpStatus.OK);
	}
	
	@GetMapping("/readBy/user_id:{userId}/route_date:{routeDate}")
	public ResponseEntity<List<ClimbDto>> readDate(@PathVariable Long userId, @PathVariable Date routeDate) {
		List<ClimbDto> climbs = this.service.readDate(userId, routeDate);
		return new ResponseEntity<>(climbs, HttpStatus.OK);
	}
	
	@GetMapping("/readBy/user_id:{userId}/route_completed:{completed}")
	public ResponseEntity<List<ClimbDto>> readCompleted(@PathVariable Long userId, @PathVariable Boolean completed) {
		List<ClimbDto> climbs = this.service.readCompleted(userId, completed);
		return new ResponseEntity<>(climbs, HttpStatus.OK);
	}
	
	//route id
	//time taken
	//data climbed
	//completed climb
}
