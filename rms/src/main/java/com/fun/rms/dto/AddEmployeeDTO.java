package com.fun.rms.DTO;

public class AddEmployeeDTO {

	private String firstName;
	private String lastName;
	private String loginCode;
	private String managerLoginCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getManagerLoginCode() {
		return managerLoginCode;
	}

	public void setManagerLoginCode(String managerLoginCode) {
		this.managerLoginCode = managerLoginCode;
	}
}
