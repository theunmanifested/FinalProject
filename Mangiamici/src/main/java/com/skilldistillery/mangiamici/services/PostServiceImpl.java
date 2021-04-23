package com.skilldistillery.mangiamici.services;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skilldistillery.mangiamici.entities.Post;
import com.skilldistillery.mangiamici.entities.Restaurant;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.PostRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Post> index() {
		return postRepo.findAll();
	}
	
//	@Override
//	public Post showById(String username, int postId) {
//		return postRepo.findByUser_UsernameAndId(username, postId);
//	}
	
	@Override
	public Post create(String username, Post post) {
		User user = userRepo.findByUsername(username);
		post.setUser(user);
		post = postRepo.saveAndFlush(post);
		return post;
	}

	@Override
	public Post update(String username, int postId, Post post) {
		Post managed = postRepo.findByUser_UsernameAndId(username, postId);
		if (managed != null) {
			managed.setUser(post.getUser());
			managed.setEventDate(post.getEventDate());
			managed.setPostText(post.getPostText());
			managed.setFlagged(post.getFlagged());
			postRepo.saveAndFlush(managed);
			return managed;			
		}		
		return null;
	}


	
	

}
