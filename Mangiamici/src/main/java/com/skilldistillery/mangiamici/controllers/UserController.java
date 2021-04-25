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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.services.UserService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class UserController {

	@Autowired
	private UserService	userSvc;
		
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	
	// DEV NOTE: We don't need CREATE as that is handled by authorize in the AuthController.
	
	@GetMapping("pub/users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res) {
		List<User> users = userSvc.index();
		System.out.println(users + " ****************************");
		if (users == null) {
			res.setStatus(404);
		}
		
		return users;
	}
	
	@GetMapping("/users")
	public User getUser(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		User user = userSvc.getByUsername(principal.getName());
		System.out.println(user + " ****************************");
		
		if (user == null) {
			res.setStatus(404);
		}
		
		return user;
	}

	@GetMapping("users/{username}")
	public User getByUsername(HttpServletRequest req, HttpServletResponse res, @PathVariable String username) {
		User user = userSvc.getByUsername(username);
		
		if (user == null) {
			res.setStatus(404);
		}
		
		return user;
	}
	
	@PutMapping("user")
	public User update(@RequestBody User user, Principal principal, HttpServletResponse resp, HttpServletRequest req) {
		
		System.out.println("Update **************************************************");
		
		user = userSvc.update(principal.getName(), user);
		if(user != null) {
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			resp.setHeader("Location", url.toString());
		}
		else { resp.setStatus(404); }
		return user;
	}
	
	@DeleteMapping("user")
	public void deleteRunForUser(Principal principal, HttpServletResponse resp) {
		
		if (userSvc.destroy(principal.getName()) != null) {
			resp.setStatus(204);
		}
		else {
			resp.setStatus(404);
		}
	}
	
}
