package com.skilldistillery.mangiamici.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserStatusTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserStatus user;	
	
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
		user = em.find(UserStatus.class, 1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals(1, user.getId());
		assertEquals("hungry", user.getName());
	}

}
