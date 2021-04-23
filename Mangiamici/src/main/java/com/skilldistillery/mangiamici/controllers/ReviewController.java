package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.services.ReviewService;
import com.skilldistillery.mangiamici.services.UserService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class ReviewController {

	@Autowired
	private ReviewService svc;
	
	@Autowired
	private UserService userSvc;
	
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

	@PostMapping("reviews")
	public Review create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Review rev ){
		
		
		
		rev = svc.create( principal.getName() , rev);
		
		if (rev != null) {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(rev.getId());
			res.setHeader("Location", url.toString());
		}
		else {
			res.setStatus(404);
		}
		return rev;
	}
	
	@DeleteMapping("reviews/{rId}")
	public void deleteRunForUser(@PathVariable Integer rId, Principal principal, HttpServletResponse resp) {
		
		if (svc.destroy(principal.getName(), rId) != null) {
			resp.setStatus(204);
		}
		else {
			resp.setStatus(404);
		}
	}
	
}
