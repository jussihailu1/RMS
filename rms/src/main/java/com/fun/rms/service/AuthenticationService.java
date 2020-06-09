package com.fun.rms.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fun.rms.security.JwtUtil;

@Service
@Transactional
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String loginCode = userService.findByLoginCode(username).getLoginCode();
		return new org.springframework.security.core.userdetails.User(loginCode, loginCode, new ArrayList<>());
	}

	public String authenticate(String loginCode) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginCode, loginCode);
		authenticationManager.authenticate(authToken);
		final UserDetails userDetails = loadUserByUsername(loginCode);
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return jwt;
	}

}
