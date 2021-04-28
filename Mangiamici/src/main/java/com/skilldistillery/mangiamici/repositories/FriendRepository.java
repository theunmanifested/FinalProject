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
	
//	@Query("    select CASE WHEN f.user.username = :user"
//			+ " THEN f.otheruser "
//			+ " ELSE f.user "
//			+ " END "
//			+ " from friend f "
//			+ " where ( f.user.username = :user or f.otheruser.username = :user ) "
//			+ " AND f.accepted = true "
//			+ " ORDER BY f.dateApproved")
//	public List<User> findUserFriends(@Param("user") String username);
	
	
	// get all friendships where both users are enabled and the friendship is accepted 
	@Query("select f from Friend f left join User u on f.user = u left join User o on f.otherUser = o where (f.approved = true) and (u.enabled = true) and (o.enabled = true)")
	public List<Friend> queryAllActiveFriendships();
	
	// get all friendships where both users are enabled and the friendship is accepted 
	// and one of the users is a given user
	@Query("select f from Friend f left join User u on f.user = u left join User o on f.otherUser = o "
		+ " where (f.approved = true) and (u.enabled = true) and (o.enabled = true) "
		+ " and (f.user.username = :username or f.otherUser.username = :username) ")
	public List<Friend> queryActiveFriendshipsForUser(@Param("username") String username);

	public List<Friend> findByApprovedFalseAndOtherUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrue(String username);
	
}






