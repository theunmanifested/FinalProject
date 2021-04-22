package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.services.ReviewService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class ReviewController {

	@Autowired
	private ReviewService svc;
	
	@GetMapping("pub/reviews/all")
	public List<Review> allPublic(HttpServletRequest req, HttpServletResponse res) {
		List<Review> reviews = svc.findAllEnabledAndPublic();

		if (reviews == null) {
			res.setStatus(404);
		}
		return reviews;
	}
	
	@GetMapping("reviews")
	public List<Review> reviewsForUser(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<Review> todos = svc.retrieveAllForUser(principal.getName());

		if (todos == null) {
			res.setStatus(404);
		}
		return todos;
	}
	
}
