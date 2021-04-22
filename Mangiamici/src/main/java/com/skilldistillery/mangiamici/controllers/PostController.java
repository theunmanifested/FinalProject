package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Post;
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
	
//  GET posts
	@GetMapping("posts")
		public List<Post> index() {
		return postSvc.index();
	}
}
