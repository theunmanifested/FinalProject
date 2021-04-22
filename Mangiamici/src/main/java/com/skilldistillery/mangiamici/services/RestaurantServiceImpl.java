package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Restaurant;
import com.skilldistillery.mangiamici.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Override
	public List<Restaurant> index() {		
		return restaurantRepo.findAll();
	}

	@Override
	public Restaurant show(String name) {
		// TODO Auto-generated method stub
		return restaurantRepo.findByName(name);
	}
}
