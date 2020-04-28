package com.fun.rms.controller;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.model.Reservation;
import com.fun.rms.model.Session;
import com.fun.rms.model.Table;
import com.fun.rms.service.ReservationService;
import com.fun.rms.service.SessionService;
import com.fun.rms.service.TableService;

@RestController
@RequestMapping("sessions")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionController {

	@Autowired
	private SessionService service;


	@PostMapping
	public String create() {
		
		service.create(2, 5, "Pieter");

		return  "Succes";
	}
	
	
	@PutMapping
	public String end() {
		service.end(25);
		return "Succes";
	}
	
}
