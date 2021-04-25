package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public List<Review> findByEnabledTrueAndIsPublicTrue();
	public List<Review> findByEnabledTrueAndUser_Username(String name);
	public Review findByUser_UsernameAndIdAndEnabledTrue(String username, int rId);
	
	@Query("select r from Review r inner join Friend u on (r.user = u.user) or (r.user = u.otherUser) where (r.user = u.user) or (r.user = u.otherUser)")
	public List<Review> findFriendsReviews(String username);
}
