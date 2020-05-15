package com.fun.rms.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String loginCode = userService.findByLoginCode(username).getLoginCode();
		return new org.springframework.security.core.userdetails.User(loginCode, loginCode, new ArrayList<>());
	}
	
	public Boolean loginCodeExists(String loginCode) {
		return userService.loginCodeExists(loginCode);
	}
	
}
