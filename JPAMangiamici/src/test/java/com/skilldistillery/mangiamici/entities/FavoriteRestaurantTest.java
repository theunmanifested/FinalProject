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

class FavoriteRestaurantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private FavoriteRestaurant item;	
	
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
		item = em.createQuery("select f from FavoriteRestaurant f where f.user.id = 5 and f.restaurant.id = 1", FavoriteRestaurant.class).getSingleResult();
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		item = null;
	}

	@Test
	void test() {
		assertNotNull(item);
		assertEquals("foodtruckfinder", item.getUser().getUsername());
	
	}
}
