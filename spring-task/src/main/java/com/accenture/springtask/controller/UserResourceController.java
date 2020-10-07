package com.accenture.springtask.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.springtask.exception.UserNotFoundException;
import com.accenture.springtask.model.User;
import com.accenture.springtask.service.UserDaoService;

@RestController
public class UserResourceController {

	private UserDaoService service;

	public UserResourceController(UserDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		User user = service.findUserById(id);
		if(user  == null) {
			throw new UserNotFoundException("id: "+id);
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = service.deleteUserById(id);
		
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
	}

}
