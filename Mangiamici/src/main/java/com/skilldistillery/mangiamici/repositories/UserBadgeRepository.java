package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.UserBadge;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Integer> {

}
