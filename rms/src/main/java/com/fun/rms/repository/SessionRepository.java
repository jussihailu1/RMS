package com.fun.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.rms.model.Session;

public interface SessionRepository  extends JpaRepository<Session, Integer> {

}
