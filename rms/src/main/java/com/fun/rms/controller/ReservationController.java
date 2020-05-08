package com.fun.rms.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.DTO.ResponseDTO;
import com.fun.rms.enums.Response;
import com.fun.rms.model.Reservation;
import com.fun.rms.service.ReservationService;

@RestController
@RequestMapping("reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

	@Autowired
	private ReservationService service;

	private void createRandomReservations(Integer amount) {
		List<String> names = new ArrayList<String>();

		names.add("Jan");
		names.add("Jaap");
		names.add("Henk");
		names.add("Thomas");
		names.add("Harm");

		for (int i = 0; i < amount; i++) {
			service.create(i + 2, LocalDate.now().plusWeeks(i), LocalTime.now().plusHours(i * 3), names.get(i));
		}
	}

	@PostMapping
	public ResponseEntity<ResponseDTO> add() {
		try {
			createRandomReservations(5);
//			service.create(i + 2, LocalDate.now().plusWeeks(i + 5), LocalTime.now().plusHours(i * (3 + 5)), names.get(i));
			return ResponseEntity.ok(ResponseDTO.send(Response.SUCCES));
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public List<Reservation> findAll() {
		return service.findAll();
	}

	@GetMapping(path = "/next7Days")
	public ResponseEntity<?> findNext7Days() {
		try {
			return ResponseEntity.ok(service.findNext7Days());
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{name}")
	public Reservation findByName(@PathVariable String name) {
		return service.findByName(name);
	}

}
