package com.fun.rms.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.fun.rms.service.UserService;
import com.fun.rms.model.User;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody String logincode) {
		try {
			
			if (service.signIn(logincode)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.OK);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> add(@RequestBody User user) {
		try {
			service.addUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.BAD_REQUEST);
		}
	}

//	---------------------------------------------------------------------------------------
	
//	@GetMapping() 
//	public ResponseEntity<User> get(@PathVariable Integer id) {
//		
//	}

	@GetMapping()
	public List<User> getAll() {
		return service.getAll();
	}
//
//	@GetMapping(path = "/{id}")
//	public ResponseEntity<UserDAO> get(@PathVariable Integer id) {
//		UserDAO user = service.get(id);
//		return new ResponseEntity<UserDAO>(user, HttpStatus.OK);
//	}
//
//	@PostMapping()
//	public ResponseEntity<UserDAO> add(@RequestBody UserDAO user) {
//		service.save(user);
//		return new ResponseEntity<UserDAO>(user, HttpStatus.OK);
//	}
//
//	@PutMapping()
//	public ResponseEntity<UserDAO> update(@RequestBody UserDAO user) {
//		UserDAO updatedUser = service.update(user);
//		return new ResponseEntity<UserDAO>(updatedUser, HttpStatus.OK);
//	}
//
//	@DeleteMapping()
//	public String delete() {
//		return "Delete was called";
//	}
}
