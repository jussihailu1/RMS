package com.fun.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.dto.ResponseDTO;
import com.fun.rms.dto.authorization.AuthenticationRequest;
import com.fun.rms.dto.authorization.AuthenticationResponse;
import com.fun.rms.enums.Response;
import com.fun.rms.service.AuthenticationService;
import com.fun.rms.service.UserService;

@RestController
@RequestMapping("authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		if (userService.loginCodeExists(authenticationRequest.getLoginCode())) {
			String jwt = authenticationService.authenticate(authenticationRequest.getLoginCode());
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} else {
			return ResponseEntity.ok(ResponseDTO.send(Response.WRONG_CREDENTIALS));
		}
	}
	
}
