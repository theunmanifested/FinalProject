package com.skilldistillery.mangiamici.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.repositories.FriendRepository;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendRepository friendRepo;
}
