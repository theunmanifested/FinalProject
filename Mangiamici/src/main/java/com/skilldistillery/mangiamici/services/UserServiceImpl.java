package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> index() {
		
		return userRepo.findByEnabledTrue();
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsernameAndEnabledTrue(username);
	}
	
}
