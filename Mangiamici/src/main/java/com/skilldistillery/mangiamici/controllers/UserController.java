package com.skilldistillery.mangiamici.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.services.UserService;

@RequestMapping("api")
@RestController
public class UserController {

	private UserService	userSvc;
		
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
}
