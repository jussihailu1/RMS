package com.fun.rms.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.model.Reservation;
import com.fun.rms.service.ReservationService;

@RestController
@RequestMapping("reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	@PostMapping
	public String add() {
		service.create(4, LocalDate.now(), LocalTime.now(), "Pieter");		
		return "Succes";
	}
	
	@GetMapping
	public List<Reservation> findAll() {
		return service.findAll();
	}
}
