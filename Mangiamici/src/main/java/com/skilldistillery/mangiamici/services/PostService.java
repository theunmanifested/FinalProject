package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.Post;

public interface PostService {

	List<Post> index();
		
	Post create(String username, Post post);
	
	Post update(String username, int postId, Post post);
	
	boolean destroy(String username, int postId);

	
}
