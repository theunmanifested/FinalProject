package com.skilldistillery.mangiamici.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("MangiamiciPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 4);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals(4, user.getId());
		assertEquals("pizzathehut", user.getUsername());
		assertTrue(user.getReviewComments().size() > 0);
	}

	@Test
	void testGettingPostTextStartingInUserEntity() {
		assertNotNull(user);
		assertEquals("Anybody down for a late night meal at BokBoks? " + "I heard good things",
				user.getPosts().get(0).getPostText());
	}

	@Test
	void testGettingRoleOfUserEntity() {
		assertNotNull(user);
		assertEquals("standard", user.getRole());
	}

	@Test
	void testGettingStatusOfUserEntity() {
		assertNotNull(user);
		assertEquals(1, user.getUserStatus().getId());
	}
	
	@Test
	@DisplayName("User badge mapping")
	void testX() {
		User user2 = em.find(User.class, 5);
		assertNotNull(user2);
		assertEquals("McKinnon", user2.getLastName());
//		assertTrue(user.getUserBadges().size() > 0);
	}

}
