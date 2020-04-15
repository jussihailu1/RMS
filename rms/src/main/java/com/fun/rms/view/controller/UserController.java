package com.fun.rms.view.controller;

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

import com.fun.rms.model.User;
import com.fun.rms.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody String code) {
		try {
			if (service.login(code)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.OK);
		}
	}

//	---------------------------------------------------------------------------------------

	@GetMapping()
	public List<User> getAll() {
		return service.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id) {
		User user = service.get(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<User> add(@RequestBody User user) {
		service.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<User> update(@RequestBody User user) {
		User updatedUser = service.update(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping()
	public String delete() {
		return "Delete was called";
	}
}
