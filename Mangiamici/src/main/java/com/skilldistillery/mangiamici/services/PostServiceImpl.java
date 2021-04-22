package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Post;
import com.skilldistillery.mangiamici.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Post> index() {
		return postRepo.findAll();
	}

}
