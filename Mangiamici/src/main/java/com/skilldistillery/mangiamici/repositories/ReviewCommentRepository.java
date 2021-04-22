package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.ReviewComment;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {

}
