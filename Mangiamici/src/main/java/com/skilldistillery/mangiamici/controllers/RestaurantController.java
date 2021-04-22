package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// GET all restaurants
	@GetMapping(path = "restaurants/{name}")
	public Restaurant showAll(HttpServletRequest req, HttpServletResponse res, @PathVariable String name) {
		Restaurant restaurant = restaurantSvc.showByName(name);
		if (restaurant == null) {
			res.setStatus(400);
		}
		return restaurant;
	}

	// GET all restaurants based on Category
	@GetMapping(path = "restaurants/bycat/{cat}")
	public List<Restaurant> showByCat(HttpServletRequest req, HttpServletResponse res, @PathVariable String cat) {
		List<Restaurant> restaurants = restaurantSvc.showByCat(cat);
		
		
			if (restaurants == null) {
				res.setStatus(400);
			} else {
				res.setStatus(200);
			}
		
		return restaurants;
	}

	// POST restaurants
	@PostMapping("restaurants")
	public Restaurant create(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantSvc.create(principal.getName(), restaurant);
		} catch (Exception e) {
			System.err.println(e);
			restaurant = null;
		}
		return restaurant;
	}

}
