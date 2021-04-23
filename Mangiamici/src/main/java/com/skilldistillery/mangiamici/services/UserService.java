package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.User;

public interface UserService {
	
	List<User> index();
	User getByUsername(String username);
	User update(String username, User user);
	User destroy(String username, int uId);
}
