package com.fun.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Session;

@Repository
public interface SessionRepository  extends JpaRepository<Session, Integer> {

}
