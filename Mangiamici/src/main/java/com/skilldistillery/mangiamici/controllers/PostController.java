package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Post;
import com.skilldistillery.mangiamici.entities.Restaurant;
import com.skilldistillery.mangiamici.services.PostService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class PostController {

	@Autowired
	private PostService postSvc;

	@GetMapping("pingPost")
	public String ping() {
		return "pongPost";
	}

//  GET a list of all the posts
	@GetMapping("posts")
	public List<Post> index() {
		return postSvc.index();
	}

//	// GET post by Id
//	@GetMapping(path="posts/{postId}")
//	public Post showById(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int postId) {
//		Post post = postSvc.showById(principal.getName(), postId);
//		if (post == null) {
//			res.setStatus(404);
//		}
//		return post;
//	}
	
	// POST posts
	@PostMapping("posts")
	public Post create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Post post) {
		try {
			post = postSvc.create(principal.getName(), post);
		} catch (Exception e) {
			System.err.println(e);
			post = null;
		}
		return post;
	}
	
//  PUT restaurants/{restaurantId}
	@PutMapping("posts/{postId}")
	public Post update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int postId, @RequestBody Post post) {
		post = postSvc.update(principal.getName(), postId, post); 
		if (post == null) {
			res.setStatus(400);
		}
		return post;
	}

}
