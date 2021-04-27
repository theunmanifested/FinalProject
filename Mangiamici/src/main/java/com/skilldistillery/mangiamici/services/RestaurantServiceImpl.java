package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Restaurant;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.LocationRepository;
import com.skilldistillery.mangiamici.repositories.RestaurantRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LocationRepository locationRepo;

	@Override
	public List<Restaurant> index() {		
		return restaurantRepo.findAll();
	}

	@Override
	public Restaurant showByName(String name) {
		return restaurantRepo.findByName(name);
	}

	@Override
	public Restaurant create(String username, Restaurant restaurant) {
		User user = userRepo.findByUsername(username);
		restaurant.setUser(user);
		locationRepo.saveAndFlush(restaurant.getLocation());
		restaurant = restaurantRepo.saveAndFlush(restaurant);
		return restaurant;
	}

	@Override
	public List<Restaurant> showBySearch(String cat) {
		return restaurantRepo.findByCategoriesContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(cat, cat, cat);
	}

	@Override
	public Restaurant update(String username, int restaurantId, Restaurant restaurant) {
		Restaurant managed = restaurantRepo.findByUser_UsernameAndId(username, restaurantId);
		if(managed != null) {
			managed.setUser(restaurant.getUser());
			managed.setLocation(restaurant.getLocation());
			managed.setPhone(restaurant.getPhone());
			managed.setEmail(restaurant.getEmail());
			managed.setWebsiteUrl(restaurant.getWebsiteUrl());
			managed.setEnabled(restaurant.isEnabled());
			managed.setCategories(restaurant.getCategories());
			managed.setImgUrl(restaurant.getImgUrl());
			managed.setName(restaurant.getName());
			managed.setDescription(restaurant.getDescription());
			managed.setMenuUrl(restaurant.getMenuUrl());
			restaurantRepo.saveAndFlush(managed);
			return managed;
		}
		return null;
	}
	
	@Override
	public boolean destroy(String username, int restaurantId) {
		boolean deleted = false;
		Restaurant r = restaurantRepo.findByUser_UsernameAndId(username, restaurantId);
		if (r != null) {
			restaurantRepo.delete(r);
			deleted = true;
		}
		return deleted;
	}
	
}
