package com.fun.rms.DTO;

public class UpdateLoginCodeDTO {
	private String oldLoginCode;
	private String newLoginCode;

	public String getOldLoginCode() {
		return oldLoginCode;
	}

	public void setOldLoginCode(String oldLoginCode) {
		this.oldLoginCode = oldLoginCode;
	}

	public String getNewLoginCode() {
		return newLoginCode;
	}

	public void setNewLoginCode(String newLoginCode) {
		this.newLoginCode = newLoginCode;
	}
}
