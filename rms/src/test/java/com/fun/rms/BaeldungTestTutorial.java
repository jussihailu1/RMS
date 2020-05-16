package com.fun.rms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fun.rms.enums.Role;
import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.User;
import com.fun.rms.repository.UserRepository;
import com.fun.rms.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
class BaeldungTestTutorial {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Test
	public void whenFindByLoginCode_thenReturnUser() {
		// given
		User user = new User("test", "test", "5555", Role.EMPLOYEE);
		entityManager.persist(user);
		entityManager.flush();

		// when
		User found = userRepository.findByLoginCode(user.getLoginCode());

		// then
		assertThat(found.getLoginCode()).isEqualTo(user.getLoginCode());
	}
	

//	@TestConfiguration
//	static class UserServiceTestContextConfiguration {
//
//		@Bean
//		public UserService userService() {
//			return new UserService();
//		}
//		
//		@Bean public ModelFactory modelFactory() {
//			return new ModelFactory();
//		}
//	}
//
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private UserService userService;
//
//	@MockBean
//	private UserRepository userRepository;
//
//	@Before
//	public void setUp() {
//		User user = new User("test", "test", "5555", Role.MANAGER);
//
//		Mockito.when(userRepository.findByLoginCode(user.getLoginCode())).thenReturn(user);
//	}
//	
//	@Test
//	public void whenValidId_thenUserShouldBeFound() {
//	    Role role = Role.MANAGER;
//	    User found = userService.findManager();
//	  
//	     assertThat(found.getRole())
//	      .isEqualTo(role);
//	 }

//	@Test
//	public void whenFindByLoginCode_thenReturnUser() {
//		// given
//		User user = new User();
//		user.setLoginCode("5555");
//		entityManager.persist(user);
//		entityManager.flush();
//
//		// when
//		User found = userRepository.findByLoginCode(user.getLoginCode());
//
//		// then
//		assertThat(found.getLoginCode()).isEqualTo(user.getLoginCode());
//	}
}
