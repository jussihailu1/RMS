//package com.fun.rms.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fun.rms.enums.Role;
//import com.fun.rms.model.User;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class UserRepositoryTests {
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired 
//	private UserRepository userRepository;
//	
//	@Test
//	public void whenFindByLoginCode_thenReturnUser() {
//		// given
//		User user = new User("test", "test", "5555", Role.EMPLOYEE);
//		entityManager.persist(user);
//		entityManager.flush();
//
//		// when
//		User found = userRepository.findByLoginCode(user.getLoginCode());
//
//		// then
//		assertThat(found.getLoginCode()).isEqualTo(user.getLoginCode());
//	}
//	
//}
