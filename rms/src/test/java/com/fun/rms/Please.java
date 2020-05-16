package com.fun.rms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fun.rms.enums.Role;
import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.User;
import com.fun.rms.repository.UserRepository;
import com.fun.rms.service.UserService;

@SpringBootTest
public class Please {
	@Mock
	UserRepository userRepository;

	@Mock
	ModelFactory modelFactory;

	@InjectMocks
	UserService userService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
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
}
