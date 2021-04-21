package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.FriendTest;

public interface FriendRepository extends JpaRepository<FriendTest, Integer> {

}
