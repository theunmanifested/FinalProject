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

class ReviewCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ReviewComment post;	
	
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
		post = em.find(ReviewComment.class, 1);
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	@Test
	void test() {
		assertNotNull(post);
		assertEquals( "Heck yeah, I'm headed there this weekend...thanks!", post.getCommentText());
		assertEquals("Best fried chicken I've found in Denver so far...and its Korean style! Try the spicy, it's worth it.", post.getReview().getReviewText());
		assertEquals( "pizzathehut", post.getUser().getUsername());
	}

}
