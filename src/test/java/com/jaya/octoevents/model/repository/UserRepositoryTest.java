package com.jaya.octoevents.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaya.octoevents.model.User;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

	@Inject
	private UserRepository userRepository;
	
	private static User user;
	
	@BeforeAll
	static void initAll() {
		user = createUserToTest();
	}
	
	private static User createUserToTest() {
		User user = new User();
		user.setId(999999999);
		user.setAvatarUrl("http://avatar.com");
		user.setLogin("master");
		user.setNodeId("Node Id 23452345");
		user.setType("Type");
		return user;
	}
	
	@Test
	@Order(1)
	void testInsertAndSearchUser() {
		User userSaved = userRepository.save(user);
		User userActual = userRepository.findById(userSaved.getId()).orElse(null);
		assertNotNull(userActual);
		assertEquals(userSaved.getId(), userActual.getId());
		assertNotNull(userActual.getAvatarUrl());
		assertNotNull(userActual.getLogin());
		assertNotNull(userActual.getNodeId());
		assertNotNull(userActual.getType());
	}
	
	@Test
	@Order(2)
	void testUpdateUser() {
		user.setAvatarUrl("http://avatar3.com");
		user.setLogin("root");
		user.setNodeId("Node Id 45");
		user.setType("Type S");
		User userUpdated = userRepository.save(user);
		User userActual = userRepository.findById(userUpdated.getId()).orElse(null);
		assertNotNull(userActual);
		assertEquals(userUpdated.getId(), userActual.getId());
		assertEquals(userActual.getAvatarUrl(), userUpdated.getAvatarUrl());
		assertEquals(userActual.getLogin(),userUpdated.getLogin());
		assertEquals(userActual.getNodeId(),userUpdated.getNodeId());
		assertEquals(userActual.getType(),userUpdated.getType());	
	}

	@Test
	@Order(3)
	void testDeleteUser() {
		userRepository.delete(user);
		User UserActual = userRepository.findById(user.getId()).orElse(null);
		assertNull(UserActual);
	}

}
