package com.fun.rms.factory;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fun.rms.enums.Role;
import com.fun.rms.model.*;

@Service
@Transactional
public class ModelFactory {

	public Reservation createReservation(Integer customers, LocalDate date, LocalTime time, String name) {
		return new Reservation(customers, date, time, name);
	}

	public Session createSession(Integer customers, Integer tableNumber, LocalTime start, LocalDate date) {
		return new Session(customers, tableNumber, start, date);
	}

	public User createUser(String firstName, String lastName, String loginCode, Role role) {
		return new User(firstName, lastName, loginCode, role);
	}
}
