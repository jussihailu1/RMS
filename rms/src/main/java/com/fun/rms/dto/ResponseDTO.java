package com.fun.rms.dto;

import org.springframework.http.ResponseEntity;

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
	
	public static ResponseEntity<ResponseDTO> succes(){
		return ResponseEntity.ok(ResponseDTO.send(Response.SUCCES));
	}
	
	public static ResponseEntity<ResponseDTO> error(){
		return ResponseEntity.ok(ResponseDTO.send(Response.SERVER_ERROR));
	}
}
