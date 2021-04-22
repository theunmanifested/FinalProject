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

import com.skilldistillery.mangiamici.entities.Restaurant;
import com.skilldistillery.mangiamici.services.RestaurantService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantSvc;
	
	// GET all restaurants
	@GetMapping("restaurants")
	public List<Restaurant> showAll(HttpServletRequest req, HttpServletResponse res) {
		return restaurantSvc.index();
	}
	
	
}
