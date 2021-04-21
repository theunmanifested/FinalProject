package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
