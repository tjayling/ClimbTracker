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

import com.qa.climbtracker.domain.model.User;
import com.qa.climbtracker.domain.dto.UserDto;
import com.qa.climbtracker.service.UserService;

@RestController()
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	private UserService service;

	@Autowired
		public UserController(UserService service) {
			this.service = service;
		}

	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody User user) {
		return new ResponseEntity<>(this.service.create(user), HttpStatus.CREATED);
	}

	@PostMapping("/createMany")
	public ResponseEntity<List<UserDto>> createMany(@RequestBody List<User> users) {
		return new ResponseEntity<>(this.service.createMany(users), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<UserDto>> readAll() {
		return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<UserDto> update(@RequestBody User user) {
		return new ResponseEntity<>(this.service.update(user), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{ids}")
	public ResponseEntity<Boolean> delete(@PathVariable List<Long> ids) {
		return this.service.delete(ids) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/readBy/id:{id}")
	public ResponseEntity<List<UserDto>> readId(@PathVariable Long id) {
		List<UserDto> users = this.service.readId(id);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/readBy/username:{username}")
	public ResponseEntity<List<UserDto>> readUsername(@PathVariable String username) {
		List<UserDto> users = this.service.readUsername(username);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// id
	// first name
	// last name
	// username
	// password
}
