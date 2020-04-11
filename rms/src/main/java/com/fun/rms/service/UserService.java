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
	public User get(Integer id) {
		return repo.findById(id).get();
	}

	// Create
	public void save(User user) {
		repo.save(user);
	}

	// Update
	public User update(User userDataToUpdate) {
		User userToUpdate = repo.findById(userDataToUpdate.getId()).get();

		if (userDataToUpdate.getFirst_name() != null) {
			userToUpdate.setFirst_name(userDataToUpdate.getFirst_name());
		}
		if (userDataToUpdate.getLast_name() != null) {
			userToUpdate.setLast_name(userDataToUpdate.getLast_name());
		}
		if (userDataToUpdate.getLogin_code() != null) {
			userToUpdate.setLogin_code(userDataToUpdate.getLogin_code());
		}
		
		repo.save(userToUpdate);
		return userToUpdate;
	}

	// Delete (by id)
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
