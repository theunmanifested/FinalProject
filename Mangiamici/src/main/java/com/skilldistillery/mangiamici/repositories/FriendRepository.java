package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

}
