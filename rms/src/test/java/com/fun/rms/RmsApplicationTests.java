package com.fun.rms;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RmsApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals(1, 1);
	}

}
