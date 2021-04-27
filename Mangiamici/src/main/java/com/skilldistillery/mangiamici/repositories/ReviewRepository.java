package com.skilldistillery.mangiamici.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.mangiamici.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public List<Review> findByEnabledTrueAndIsPublicTrue();
	public List<Review> findByEnabledTrueAndUser_Username(String name);
	public Review findByUser_UsernameAndIdAndEnabledTrue(String username, int rId);
	
	@Query("select r from Review r inner join Friend u on (r.user = u.user) or (r.user = u.otherUser) where ((r.user = u.user) or (r.user = u.otherUser)) and ((u.user.username = :username) or (u.otherUser.username = :username))")
	public List<Review> findFriendsReviews(@Param("username") String username);
	
	@Query("select r from Review r inner join Friend u on (r.user = u.user) or (r.user = u.otherUser) where ((r.user = u.user) or (r.user = u.otherUser)) and ((u.user.username = :username) or (u.otherUser.username = :username)) and (r.restaurant.id = :rId)")
	public List<Review> findFriendsReviewsforRestaurant(@Param("username") String username, @Param("rId") Integer restaurantId);
	
	@Query("    select r from Review r "
			+ " left join r.user u left join u.friends f"
			+ " where ((u.username != :username) "
							+ " and (f.otherUser.username != :username)) "
			+ " and (r.restaurant.id = :rId)")
	public List<Review> findNonFriendsReviewsforRestaurantX(@Param("username") String username, @Param("rId") Integer restaurantId);
	
	
	
	@Query("select r from Review r left "
			+ " join Friend u on (r.user = u.user) or (r.user = u.otherUser) "
			+ " where (((u.user.username != :username) or (u.user is null)) "
			+ " and ((u.otherUser.username != :username) or (u.otherUser is null))) "
			+ " and (r.restaurant.id = :rId)")
	public List<Review> findNonFriendsReviewsforRestaurant(@Param("username") String username, @Param("rId") Integer restaurantId);
	
	
	/*
	 * Incomplete - does not take corner cases into account
	 * 
	 */
	@Query(value = "        "
			+ "        SELECT "
			+ "    * "
			+ " FROM "
			+ "    Review  r "
			+ "        LEFT JOIN"
			+ "    User u ON (r.user_id = u.id)"
			+ "        LEFT JOIN"
			+ "    Friend f ON u.id = f.user_id"
			+ "        LEFT JOIN"
			+ "    user other_user ON f.other_user_id = other_user.id"
			+ " WHERE "
			+ "    u.username != :username"
			+ "        AND (f.other_user_id IS NULL "
			+ "        OR other_user.username != :username)"
			+ "        AND restaurant_id = :rId" , 	
			nativeQuery = true)
	public List<Review> findNonFriendsReviewsforRestaurantZ(@Param("username") String username, @Param("rId") Integer restaurantId);
	
	
}
