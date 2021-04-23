package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.FriendId;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

	public List<Friend> findByOtherUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrueAndApprovedFalse(String username);
	
	public List<Friend> findByUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrueAndApprovedFalse(String username);

	public Friend findByUser_UsernameAndUser_EnabledTrueAndOtherUser_UsernameAndOtherUser_EnabledTrue(String requester, String requested);
	
	public Friend findByUser_UsernameAndOtherUser_Username(String requester, String requested);
	
	
}
