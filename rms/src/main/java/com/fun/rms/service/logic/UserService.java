package com.fun.rms.service.logic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fun.rms.persistence.model.UserDAO;
import com.fun.rms.persistence.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

//	entitymanager
	
	// Login
	public Boolean login(String code) {
		UserDAO user = new UserDAO();
		user.setLogin_code(code);
		Example<UserDAO> example = Example.of(user);
		if (repo.count(example) == 1) {
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------------------------------

	// Get all
	public List<UserDAO> getAll() {
		return repo.findAll();
	}

	// Get by id
	public UserDAO get(Integer id) {
		return repo.findById(id).get();
	}

	// Create
	public void save(UserDAO user) {
		repo.save(user);
	}

	// Update
	public UserDAO update(UserDAO userDataToUpdate) {
		UserDAO userToUpdate = repo.findById(userDataToUpdate.getId()).get();

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
