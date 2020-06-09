package com.fun.rms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.dto.ResponseDTO;
import com.fun.rms.dto.reservation.ReservationDTO;
import com.fun.rms.enums.Response;
import com.fun.rms.model.Reservation;
import com.fun.rms.service.ReservationService;

@RestController
@RequestMapping("reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

	@Autowired
	private ReservationService service;

	@PostMapping
	public ResponseEntity<?> add(@Valid @RequestBody ReservationDTO reservation) {
		try {
			service.save(reservation.getCustomers(), reservation.getDate(), reservation.getTime(),
					reservation.getName());
			return ResponseEntity.ok(ResponseDTO.send(Response.SUCCES));
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/all")
	public List<Reservation> findAll() {
		return service.findAll();
	}

	@GetMapping(path = "/nextNDays")
	public ResponseEntity<?> findNext7Days(@RequestParam(value = "daySpan", defaultValue = "7") Integer daySpan) {
		try {
			return ResponseEntity.ok(service.findNextNDays(daySpan));
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping()
	public List<Reservation> findByName(@RequestParam(value = "name") String name) {
		return service.findByName(name);
	}

	@GetMapping(path = "/today")
	public ResponseEntity<?> findTodaysReservations() {
		try {
			return ResponseEntity.ok(service.findTodays());
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/today/notvisited")
	public ResponseEntity<?> findTodaysReservationsNotVisited() {
		try {
			return ResponseEntity.ok(service.findTodaysNotVisited());
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@RequestBody Reservation reservation) {
		service.update(reservation);
		return ResponseDTO.succes();
	}
	
	@PutMapping(path = "/{id}/toggleVisited")
	public ResponseEntity<?> toggleVisited(@PathVariable Integer id){
		service.toggleVisited(id);
		return ResponseDTO.succes();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDTO> cancel(@PathVariable Integer id) {
		try {
			service.cancel(id);
			return ResponseEntity.ok(ResponseDTO.send(Response.SUCCES));
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(ResponseDTO.send(Response.SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
