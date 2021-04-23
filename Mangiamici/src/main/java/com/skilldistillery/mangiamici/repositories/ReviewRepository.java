package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByEnabledTrueAndIsPublicTrue();
	List<Review> findByEnabledTrueAndUser_Username(String name);
	Review findByUser_UsernameAndIdAndEnabledTrue(String username, int rId);
}
