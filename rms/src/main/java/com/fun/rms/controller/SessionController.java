package com.fun.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.service.SessionService;
import com.fun.rms.model.Session;

@RestController
@RequestMapping("sessions")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionController {

	@Autowired
	private SessionService service;

	@PostMapping
	public String create() {
		service.create(2, 5, "Pieter");
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

	@GetMapping(path = "/{id}")
	public Session findById(@PathVariable Integer id) {
		return service.findById(id);
	}

}
