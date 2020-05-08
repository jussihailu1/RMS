package com.fun.rms.DTO;

import com.fun.rms.enums.Response;

public class ResponseDTO {
	
	private String message;

	public ResponseDTO(Response response) {
		this.message = response.toString();
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ResponseDTO send(Response response) {
		return new ResponseDTO(response);
	}
}
