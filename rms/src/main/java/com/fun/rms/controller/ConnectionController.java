package com.fun.rms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.DTO.ResponseDTO;
import com.fun.rms.enums.Response;

@RestController
@RequestMapping("connection")
@CrossOrigin(origins = "http://localhost:4200")
public class ConnectionController {

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(ResponseDTO.send(Response.SUCCES));
	}
}
