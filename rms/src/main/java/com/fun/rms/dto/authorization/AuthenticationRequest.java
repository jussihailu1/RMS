package com.fun.rms.dto.authorization;

public class AuthenticationRequest {
	private String loginCode;

	public AuthenticationRequest() {
	}
	
	public AuthenticationRequest(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
}
