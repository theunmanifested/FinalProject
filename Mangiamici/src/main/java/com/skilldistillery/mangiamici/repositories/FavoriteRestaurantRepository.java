package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.FavoriteRestaurant;
import com.skilldistillery.mangiamici.entities.UserRestaurantId;

public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, UserRestaurantId> {

}
