package com.fun.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.service.SessionService;
import com.fun.rms.dto.ResponseDTO;
import com.fun.rms.enums.Response;
import com.fun.rms.model.Session;

@RestController
@RequestMapping("sessions")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionController {

	@Autowired
	private SessionService service;

	@PostMapping
	public String create() {
		service.create(2, 5, 35);				// HIER BEZIG: DIT AANROEPEN EN MINIMAAL 1 SESSION CREËREN.
		return "Succes";
	}

	@PutMapping
	public String end() {
		service.end(25);
		return "Succes";
	}

	@GetMapping
	public List<Session> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "/today")
	public ResponseEntity<?> findTodays(){
		try {
			return ResponseEntity.ok(service.findTodays());
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{id}")
	public Session findById(@PathVariable Integer id) {
		return service.findById(id);
	}

}
