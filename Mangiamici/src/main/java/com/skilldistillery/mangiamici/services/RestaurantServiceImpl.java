package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Restaurant;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.RestaurantRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;
	
	private UserRepository userRepo;

	@Override
	public List<Restaurant> index() {		
		return restaurantRepo.findAll();
	}

	@Override
	public Restaurant showByName(String name) {
		return restaurantRepo.findByName(name);
	}

	// FIXME Needing User Repository/Svc/Impl/Ctllr to have full CRUD ops
	@Override
	public Restaurant create(String username, Restaurant restaurant) {
		User user = userRepo.findByUsername(username);
		restaurant.setUser(user);
		restaurant = restaurantRepo.saveAndFlush(restaurant);
		return restaurant;
	}

	@Override
	public List<Restaurant> showByCat(String cat) {
		
		return restaurantRepo.findByCategoriesContainingIgnoreCase(cat);
	}
	

	
}
