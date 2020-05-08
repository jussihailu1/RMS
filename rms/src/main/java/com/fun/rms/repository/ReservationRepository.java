package com.fun.rms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>  {
	Reservation findByName(String name);
	
	@Query("FROM Reservation WHERE date >= ?1 AND date <= ?2")
	List<Reservation> findBetween(LocalDate today, LocalDate oneWeekFurther);
}
