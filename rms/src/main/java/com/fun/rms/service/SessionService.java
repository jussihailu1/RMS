package com.fun.rms.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.Reservation;
import com.fun.rms.model.Session;
import com.fun.rms.model.Table;
import com.fun.rms.repository.SessionRepository;

@Service
@Transactional
public class SessionService {

	@Autowired
	private SessionRepository repo;

	@Autowired
	private ModelFactory factory;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private TableService tableService;

	public Session findById(Integer id) {
		return repo.findById(id).get();
	}

	public void create(Integer customers, Integer tableNumber) {
		Session session = factory.createSession(customers, tableNumber, LocalTime.now(), LocalDate.now());
		Table table = tableService.findByTableNumber(tableNumber);
		table.setSession(session);
		tableService.update(table);
	}

	public void create(Integer customers, Integer tableNumber, Integer reservationId) {
		Session session = factory.createSession(customers, tableNumber, LocalTime.now(), LocalDate.now());
		Reservation reservation = reservationService.findById(reservationId);
		reservation.setVisited(true);
		session.setReservation(reservation);
		Table table = tableService.findByTableNumber(tableNumber);
		table.setSession(session);
		tableService.update(table);
	}
	
	public void end(Integer id) {
		Session session = findById(id);		
		Long durationInMinutes = Duration.between(session.getStart(), LocalTime.now()).toMinutes();
		session.setDuration(durationInMinutes);		
		Table table = tableService.findByTableNumber(session.getTableNumber());
		table.setSession(null);
		tableService.update(table);
	}
	
	public List<Session> findTodays(){
		return repo.findTodays(LocalDate.now());
	}
	
	public List<Session> findAll(){
		return repo.findAll();
	}
}
