package com.fun.rms.DTO;

import com.fun.rms.enums.Response;

public class ResponseDTO {
	
	private String message;

	public ResponseDTO(Response response) {
		this.message = response.toString();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String response) {
		this.message = response;
	}
}
