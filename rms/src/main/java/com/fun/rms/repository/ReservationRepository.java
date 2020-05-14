package com.fun.rms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>  {
	@Query("FROM Reservation WHERE canceled = false")
	List<Reservation> findAll();
	
	@Query("FROM Reservation WHERE name = ?1 and canceled = false")
	List<Reservation> findByName(String name);
	
	@Query("FROM Reservation WHERE date >= ?1 AND date <= ?2 AND canceled = false ORDER BY date ASC, time ASC")
	List<Reservation> findBetween(LocalDate today, LocalDate range);
	
	@Query("FROM Reservation WHERE date = ?1 AND canceled = false ORDER BY time ASC")
	List<Reservation> findTodays(LocalDate today);
}
