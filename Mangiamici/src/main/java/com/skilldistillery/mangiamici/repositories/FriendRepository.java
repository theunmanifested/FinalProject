package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.FriendId;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

	public List<Friend> findByOtherUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrueAndApprovedFalse(String username);
	
	public List<Friend> findByUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrueAndApprovedFalse(String username);

	public Friend findByUser_UsernameAndUser_EnabledTrueAndOtherUser_UsernameAndOtherUser_EnabledTrue(String requester, String requested);
	
	public Friend findByUser_UsernameAndOtherUser_Username(String requester, String requested);
	
	@Query("    select CASE WHEN f.user.username = : user"
			+ " THEN f.user\n"
			+ "WHEN Quantity = 30 THEN 'The quantity is 30'\n"
			+ "ELSE 'The quantity is under 30'\n"
			+ "END"
			+ ""
			+ " from friend f "
			+ " where ( f.user.username = :user or f.otheruser.username = :user ) "
			+ " AND f.accepted = true "
			+ " ORDER BY f.dateApproved")
	public List<Friend> findUserFriends(@Param("user") String username);
	
}
