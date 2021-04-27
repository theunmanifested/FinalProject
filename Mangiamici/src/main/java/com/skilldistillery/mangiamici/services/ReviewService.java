package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.Review;

public interface ReviewService {
	public List<Review> findAllEnabledAndPublic();
	public List<Review> findAllEnabledAndFriendsPrivateAndPublic();
	public List<Review> retrieveAllForUser(String username);

    public Review show(String username, int rId);

    public Review create(String username, Review review);

    public Review update(String username, int rId, Review review);

    public Review destroy(String username, int rId);
    
    public List<Review> findFriendsReviews(String username);
    
    public List<Review> findFriendsReviewsforRestaurant(String username, Integer restaurantId);
    
    public List<Review> findNonFriendsReviewsforRestaurant(String username, Integer rId);
    
    public List<Review> adminFindReviewForRestaurant( Integer rId);
	public List<Review> findAllPublicForRestaurant(Integer rId);
}
