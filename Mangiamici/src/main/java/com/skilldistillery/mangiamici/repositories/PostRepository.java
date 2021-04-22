package com.skilldistillery.mangiamici.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	
}
