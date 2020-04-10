package com.fun.rms.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.rms.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
