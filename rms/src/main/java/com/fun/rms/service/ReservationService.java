package com.fun.rms.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.DTO.ReservationDTO;
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
	
	public void save(Integer customers, LocalDate date,LocalTime time, String name) {
		Reservation reservation = factory.createReservation(customers, date, time, name);
		repo.save(reservation);
	}
	
	public Reservation findById(Integer id) {
		return repo.findById(id).get();
	}

	public List<Reservation> findByName(String name) {
		return repo.findByName(name);
	}
	
	public List<Reservation> findAll(){
		return repo.findAll();
	}
	
	public List<Reservation> findTodays(){
		return repo.findTodays(LocalDate.now());
	}
	
	public List<Reservation> findNextNDays(Integer daysToAdd){
		LocalDate today = LocalDate.now().plusDays(1);
		LocalDate range = today.plusDays(daysToAdd);
		return repo.findBetween(today, range);
	}
	
	public void update(ReservationDTO reservationDTO) {
		Reservation reservation = findById(reservationDTO.getId());
		reservation.setCustomers(reservationDTO.getCustomers());
		reservation.setDate(reservationDTO.getDate());
		reservation.setTime(reservationDTO.getTime());
		reservation.setName(reservationDTO.getName());
		repo.save(reservation);
	}
	
	public void cancel(Integer id) {
		Reservation reservation = findById(id);
		reservation.setCanceled(true);
		repo.save(reservation);
	}
}
