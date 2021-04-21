package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.services.AuthService;

@CrossOrigin({"*", "http://localhost:4210"})
@RestController
public class AuthController {
	

	@Autowired
	private AuthService authService;
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user, HttpServletResponse resp) {

	    if (user == null) {
	        resp.setStatus(400);
	    }

	    user = authService.register(user);

	    return user;
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public Principal authenticate(Principal principal) {
	    return principal;
	}


}
