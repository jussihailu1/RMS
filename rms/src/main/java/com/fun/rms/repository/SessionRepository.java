package com.fun.rms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Session;

@Repository
public interface SessionRepository  extends JpaRepository<Session, Integer> {
	@Query("FROM Session WHERE deleted = false")
	List<Session> findAll();
	
	@Query("FROM Session WHERE deleted = false AND date = ?1")
	List<Session> findTodays(LocalDate date);
}
