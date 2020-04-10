package com.fun.rms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.model.User;
import com.fun.rms.persistence.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	// Get all
	public List<User> getAll() {
		return repo.findAll();
	}

	// Get by id
	public User get(String id) {
		return repo.findById(id).get();
	}

	// Create or update (by id)
	public void save(User user) {
		repo.save(user);
	}

	// Delete (by id)
	public void delete(String id) {
		repo.deleteById(id);
	}
}
