package com.fun.rms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.model.User;
import com.fun.rms.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(path = "/neonfodscfds")
	public ResponseEntity<User> add(@RequestBody User user) {
		service.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping()
	public String test() {
		return "test succeded";
	}
}
