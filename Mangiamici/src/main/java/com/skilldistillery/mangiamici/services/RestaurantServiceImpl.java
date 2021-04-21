package com.skilldistillery.mangiamici.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;
}
