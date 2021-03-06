package com.skilldistillery.mangiamici.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.services.FavoriteRestaurantService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class FavoriteRestaurantController {

	@Autowired
	private FavoriteRestaurantService favoriteRestaurantSvc;
	
}
