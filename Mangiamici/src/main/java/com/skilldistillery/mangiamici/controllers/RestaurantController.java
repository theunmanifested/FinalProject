package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@Autowired
	private EntityManager em;

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

	@GetMapping(path = "pub/restaurants/{id}")
	public Restaurant getById(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id) {

		Restaurant r = em.find(Restaurant.class, id);

		if (r == null) {
			res.setStatus(404);
		}

		return r;
	}

	// GET all restaurants by category, name, description
	@GetMapping(path = "restaurants/bysearch/{cat}")
	public List<Restaurant> showBySearch(HttpServletRequest req, HttpServletResponse res, @PathVariable String cat) {
		List<Restaurant> restaurants = restaurantSvc.showBySearch(cat);
		if (restaurants == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
		}
		return restaurants;
	}

	// GET all restaurants by category, name, description
	@GetMapping(path = "pub/restaurants/bysearch/{cat}")
	public List<Restaurant> showBySearchPublic(HttpServletRequest req, HttpServletResponse res,
			@PathVariable String cat) {
		List<Restaurant> restaurants = restaurantSvc.showBySearch(cat);
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

//  PUT restaurants/{restaurantId}
	@PutMapping("restaurants/{restaurantId}")
	public Restaurant update(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable int restaurantId, @RequestBody Restaurant restaurant) {
		restaurant = restaurantSvc.update(principal.getName(), restaurantId, restaurant);
		if (restaurant == null) {
			res.setStatus(400);
		}
		return restaurant;
	}

//  DELETE restaurants/{restaurantId}
	@DeleteMapping("restaurants/{restaurantId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable int restaurantId) {
		if (restaurantSvc.destroy(principal.getName(), restaurantId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}

	}

}
