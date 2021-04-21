package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {
	
	

}
