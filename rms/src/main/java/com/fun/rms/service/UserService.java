package com.fun.rms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.repository.UserRepository;
import com.fun.rms.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	// Login
	public Boolean signIn(String logincode) {		
		User user = repo.findByLogincode(logincode);
		if(user != null) {return true;}
		else {return false;}
	}
	
	public void addUser(User user) {
		repo.save(user);
	}

	// ---------------------------------------------------------------------------------------

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

		if (userDataToUpdate.getFirstname() != null) {
			userToUpdate.setFirstname(userDataToUpdate.getFirstname());
		}
		if (userDataToUpdate.getLastname() != null) {
			userToUpdate.setLastname(userDataToUpdate.getLastname());
		}
		if (userDataToUpdate.getLogincode() != null) {
			userToUpdate.setLogincode(userDataToUpdate.getLogincode());
		}

		repo.save(userToUpdate);
		return userToUpdate;
	}

	// Delete (by id)
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
