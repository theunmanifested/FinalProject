package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
