package com.fun.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.dto.ResponseDTO;
import com.fun.rms.enums.Response;
import com.fun.rms.security.models.AuthenticationRequest;
import com.fun.rms.security.models.AuthenticationResponse;
import com.fun.rms.security.util.JwtUtil;
import com.fun.rms.service.AuthenticationService;
import com.fun.rms.service.UserService;

@RestController
public class SecurityTestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		if (authenticationService.loginCodeExists(authenticationRequest.getLoginCode())) {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getLoginCode(), authenticationRequest.getLoginCode());
			authenticationManager.authenticate(authToken);
			final UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getLoginCode());
			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} else {
			return ResponseEntity.ok(ResponseDTO.send(Response.WRONG_CREDENTIALS));
		}
	}
}
