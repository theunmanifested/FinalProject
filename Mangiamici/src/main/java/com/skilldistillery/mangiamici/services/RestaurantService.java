package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.Restaurant;

public interface RestaurantService {

	List<Restaurant> index();
	
	Restaurant showByName(String name);
	
	List<Restaurant> showByCat(String cat);
	
	Restaurant create(String username, Restaurant restaurant);
}
