package com.fun.rms.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.rms.persistence.model.UserDAO;

public interface UserRepository extends JpaRepository<UserDAO, Integer> {

}
