package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	Restaurant findByName(String name);
	
	List<Restaurant> findByCategoriesContainingIgnoreCase(String cat);
	
	
}
