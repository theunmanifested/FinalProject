package com.skilldistillery.mangiamici.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Post;
import com.skilldistillery.mangiamici.entities.Restaurant;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Post findByUser_UsernameAndId(String username, Integer postId);
}
