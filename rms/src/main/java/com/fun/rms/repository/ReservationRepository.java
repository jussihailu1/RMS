package com.fun.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>  {
	Reservation findByName(String name);
	
}
