package com.skilldistillery.mangiamici.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.services.RestaurantService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class RestaurantController {

	private RestaurantService restaurantSvc;
	
	
}
