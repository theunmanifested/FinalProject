package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.Restaurant;

public interface RestaurantService {

	List<Restaurant> index();
	
	Restaurant showByName(String name);
	
	List<Restaurant> showBySearch(String cat);
	
	Restaurant create(String username, Restaurant restaurant);
	
	Restaurant update(String username, int restaurantId, Restaurant restaurant);
	
	boolean destroy(String username, int restaurantId);
}
