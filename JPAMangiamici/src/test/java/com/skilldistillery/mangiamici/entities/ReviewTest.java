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

class ReviewTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Review post;	
	
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
//		post = em.createQuery("select f from Friend f where f.otherUserId.id = 5 and f.UserId.id = 4", Friend.class).getSingleResult();
		post = em.find(Review.class, 1);
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	@Test
	void test() {
//		assertNotNull(post);
//		assertEquals("Best fried chicken I've found in Denver so far...and its Korean style! Try the spicy, it's worth it.", post.getReviewText());
	}
}
