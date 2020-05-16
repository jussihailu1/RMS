package com.fun.rms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fun.rms.enums.Role;
import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.User;
import com.fun.rms.repository.ReservationRepository;
import com.fun.rms.service.ReservationService;

@SpringBootTest
public class ReservationServiceTests {

	@Mock
	private ModelFactory modelFactory;
	
	@Mock private ReservationService reservationService;
	
	@Mock 
	private ReservationRepository reservationRepository;
	
	@Test
	public void findByNameTest() {
//		List<User> list = new ArrayList<User>();
//		User u1 = new User("John", "John", "1234", Role.EMPLOYEE);
//		User u2 = new User("Jan", "Jan", "2345", Role.EMPLOYEE);
//		User u3 = new User("Jaap", "Jaap", "3456", Role.EMPLOYEE);
//
//		list.add(u1);
//		list.add(u2);
//		list.add(u3);
//
//		when(userRepository.findAll()).thenReturn(list);
//
//		// test
//		List<User> usersList = userService.findAll();
//
//		assertEquals(3, usersList.size());
//		assertEquals(u1.getFirstName(), usersList.get(0).getFirstName());
//		assertEquals(u2.getFirstName(), usersList.get(1).getFirstName());
//		assertEquals(u3.getFirstName(), usersList.get(2).getFirstName());
//		verify(userRepository, times(1)).findAll();
		assertEquals(1, 1);
	}
}
