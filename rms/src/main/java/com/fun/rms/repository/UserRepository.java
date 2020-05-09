package com.fun.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fun.rms.enums.Role;
import com.fun.rms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByLoginCode(String loginCode);
	User findByRole(Role role);
	
	@Query("FROM User WHERE deleted = false")
	List<User> findAll();
}
