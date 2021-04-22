package com.skilldistillery.mangiamici.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("pub/users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res) {
		List<User> users = userSvc.index();
		System.out.println(users + " ****************************");
		if (users == null) {
			res.setStatus(404);
		}
		
		return users;
	}
}
