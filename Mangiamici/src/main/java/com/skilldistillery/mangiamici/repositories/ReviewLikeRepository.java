package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.ReviewLike;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Integer> {

}
