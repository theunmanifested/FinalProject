package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);  // admin only
	public User findByUsernameAndEnabledTrue(String username);
	public List<User> findByEnabledTrue();
	
}
