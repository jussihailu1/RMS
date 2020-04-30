package com.fun.rms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.repository.UserRepository;
import com.fun.rms.enums.Role;
import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private ModelFactory factory;

	// Gets
	// -----------------------------------------------------------------------------------------

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(Integer id) {
		return repo.findById(id).get();
	}

	public User findManager() {
		return repo.findByRole(Role.MANAGER);
	}

	// Functions
	// -----------------------------------------------------------------------------------------

	public Boolean login(String logincode) {
		return repo.findByLoginCode(logincode) != null;
	}

	public void addEmployee(String firstName, String lastName, String loginCode) {
		User user = factory.createUser(firstName, lastName, loginCode, Role.EMPLOYEE);
		repo.save(user);
	}

	// Checks
	// -----------------------------------------------------------------------------------------

	public Boolean checkManagerLoginCode(String managerLoginCode) {
		return findManager().getLoginCode().equals(managerLoginCode);
	}

	public Boolean checkLoginCodeAvailability(String loginCode) {
		return repo.findByLoginCode(loginCode) == null;
	}
	
	public Boolean userAlreadyInRole(Integer id, Role role) {
		return findById(id).getRole().equals(role);
	}
	
	public Boolean userIsAssistantManager(Integer id) {
		return findById(id).getRole().equals(Role.ASSISTANT_MANAGER);
	}

	// Role related
	// -----------------------------------------------------------------------------------------

	public void updateRole(Integer id, Role role) {
		User user = findById(id);
		user.setRole(role);
		repo.save(user);
	}

	public void passManagerRole(Integer managerToBeId) {
		updateRole(findManager().getId(), Role.ASSISTANT_MANAGER);
		updateRole(managerToBeId, Role.MANAGER);
	}

//	public User update(User userDataToUpdate) {
//		User userToUpdate = repo.findById(userDataToUpdate.getId()).get();
//
//		repo.save(userToUpdate);
//		return userToUpdate;
//	}
}
