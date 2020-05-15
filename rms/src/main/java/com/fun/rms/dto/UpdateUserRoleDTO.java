package com.fun.rms.dto;

import com.fun.rms.enums.Role;

public class UpdateUserRoleDTO {
	private Role role;
	private String managerLoginCode;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getManagerLoginCode() {
		return managerLoginCode;
	}

	public void setManagerLoginCode(String managerPassword) {
		this.managerLoginCode = managerPassword;
	}
}