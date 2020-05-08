package com.fun.rms.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.Reservation;
import com.fun.rms.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepository repo;
	
	@Autowired
	private ModelFactory factory;
	
	public void create(Integer customers, LocalDate date,LocalTime time, String name) {
		Reservation reservation = factory.createReservation(customers, date, time, name);
		repo.save(reservation);
	}

	public Reservation findByName(String name) {
		return repo.findByName(name);
	}
	
	public List<Reservation> findAll(){
		return repo.findAll();
	}
	
	public List<Reservation> findNext7Days(){
		LocalDate today = LocalDate.now();
		LocalDate oneWeekFurther = today.plusWeeks(1);
		return repo.findBetween(today, oneWeekFurther);
	}
}
