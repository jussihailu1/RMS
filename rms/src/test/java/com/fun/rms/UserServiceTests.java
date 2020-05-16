package com.fun.rms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.fun.rms.enums.Role;
import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.User;
import com.fun.rms.repository.UserRepository;
import com.fun.rms.service.UserService;

@SpringBootTest
class UserServiceTests {

	@Mock
	UserRepository userRepository;

	@Mock
	ModelFactory modelFactory;

	@InjectMocks
	UserService userService;

	@Test
	public void findAllTest() {
		List<User> list = new ArrayList<User>();
		User u1 = new User("John", "John", "1234", Role.EMPLOYEE);
		User u2 = new User("Jan", "Jan", "2345", Role.EMPLOYEE);
		User u3 = new User("Jaap", "Jaap", "3456", Role.EMPLOYEE);

		list.add(u1);
		list.add(u2);
		list.add(u3);

		when(userRepository.findAll()).thenReturn(list);

		// test
		List<User> usersList = userService.findAll();

		assertEquals(3, usersList.size());
		assertEquals(u1.getFirstName(), usersList.get(0).getFirstName());
		assertEquals(u2.getFirstName(), usersList.get(1).getFirstName());
		assertEquals(u3.getFirstName(), usersList.get(2).getFirstName());
		verify(userRepository, times(1)).findAll();
	}

	@Test
	public void findManagerTest() {
		when(userRepository.findByRole(Role.MANAGER)).thenReturn(new User("John", "Jannen", "1234", Role.MANAGER));

		User user = userService.findManager();

		assertEquals("John", user.getFirstName());
		assertEquals("Jannen", user.getLastName());
		assertEquals("1234", user.getLoginCode());
		assertEquals(Role.MANAGER, user.getRole());
	}

	@Test
	public void addEmployeeTest() {
		User user = modelFactory.createUser("John", "Jannen", "1234", Role.EMPLOYEE);

		userService.addEmployee("John", "Jannen", "1234");

		verify(userRepository, times(1)).save(user);
	}

//	Check test
	
//	@Test
//	public void loginCodeIsCorrectTest() {
//		Integer id = 1;
//		String loginCode = "1234";
//		Boolean result = userService.findById(id).getLoginCode().equals(loginCode);
//		
////		Do something.
//	}

//	public Boolean loginCodeExists(String loginCode) {
//		return repo.findByLoginCode(loginCode) != null;
//	}
//
//	public Boolean managerLoginCodeIsCorrect(String managerLoginCode) {
//		return loginCodeIsCorrect(findManager().getId(), managerLoginCode);
//	}
//
//	public Boolean userAlreadyInRole(Integer id, Role role) {
//		return findById(id).getRole().equals(role);
//	}
//
//	public Boolean userIsAssistantManager(Integer id) {
//		return findById(id).getRole().equals(Role.ASSISTANT_MANAGER);
//	}
}
