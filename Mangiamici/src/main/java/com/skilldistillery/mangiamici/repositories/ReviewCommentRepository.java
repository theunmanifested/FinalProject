package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.ReviewCommentTest;

public interface ReviewCommentRepository extends JpaRepository<ReviewCommentTest, Integer> {

}
