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

class BadgeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Badge badge;	
	
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
		badge = em.find(Badge.class, 1);
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		badge = null;
	}

	@Test
	void test() {
		assertNotNull(badge);
		assertEquals(1, badge.getId());
		assertEquals("Future Foodie", badge.getName());
	
	}
	
}
