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
	private ModelFactory modelFactory;

	// Gets
	// -----------------------------------------------------------------------------------------

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(Integer id) {
		return repo.findById(id).get();
	}

	public String findLoginCode(Integer id) {
		return findById(id).getLoginCode();
	}

	public User findManager() {
		return repo.findByRole(Role.MANAGER);
	}

	// Functions
	// -----------------------------------------------------------------------------------------

	public void addEmployee(String firstName, String lastName, String loginCode) {
		User user = modelFactory.createUser(firstName, lastName, loginCode, Role.EMPLOYEE);
		repo.save(user);
	}

	public void updateLoginCode(Integer id, String newLoginCode) {
		User user = findById(id);
		user.setLoginCode(newLoginCode);
		repo.save(user);
	}

	public void updateName(Integer id, String firstName, String lastName) {
		User user = findById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		repo.save(user);
	}

	// Checks
	// -----------------------------------------------------------------------------------------

	public Boolean loginCodeIsCorrect(Integer id, String loginCode) {
		return findById(id).getLoginCode().equals(loginCode);
	}

	public Boolean loginCodeExists(String loginCode) {
		return repo.findByLoginCode(loginCode) != null;
	}

	public Boolean managerLoginCodeIsCorrect(String managerLoginCode) {
		return loginCodeIsCorrect(findManager().getId(), managerLoginCode);
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
