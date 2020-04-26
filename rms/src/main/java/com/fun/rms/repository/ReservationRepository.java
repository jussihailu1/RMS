package com.fun.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.rms.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>  {

}
