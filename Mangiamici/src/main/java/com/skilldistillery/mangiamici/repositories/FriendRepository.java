package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Friend;
// FIXME change Integer to the Id 
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

}
