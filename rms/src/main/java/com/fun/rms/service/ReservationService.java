package com.fun.rms.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.model.Reservation;
import com.fun.rms.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepository repo;

	public void add(Reservation reservation) {
		repo.save(reservation);
	}
	
	public void create(Integer customers, LocalDate date,LocalTime time, String name) {
		Reservation reservation = new Reservation(customers, date, time, name);
		repo.save(reservation);
	}

	public Reservation findByName(String name) {
		return repo.findByName(name);
	}
	
	public List<Reservation> findAll(){
		return repo.findAll();
	}
}
