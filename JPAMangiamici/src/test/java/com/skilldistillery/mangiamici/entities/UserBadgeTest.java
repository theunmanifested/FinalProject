package com.skilldistillery.mangiamici.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserBadgeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserBadge userBadge;	
	
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
		userBadge = em.find(UserBadge.class, 1);
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		userBadge = null;
	}

	@Test
	void test() {
		assertNotNull(userBadge);
		assertEquals(1, userBadge.getId());
		assertEquals("McKinnon", userBadge.getUser().getLastName());
	}
}
