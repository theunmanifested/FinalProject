package com.skilldistillery.mangiamici.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.repositories.UserStatusRepository;

@Service
public class UserStatusServiceImpl implements UserStatusService {

	@Autowired
	private UserStatusRepository userStatusRepo;
}
