package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.ReviewRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public List<Review> findAllEnabledAndPublic() {

		return reviewRepo.findByEnabledTrueAndIsPublicTrue();
	}

	@Override
	public List<Review> findAllEnabledAndFriendsPrivateAndPublic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> retrieveAllForUser(String username) {

		return reviewRepo.findByEnabledTrueAndUser_Username(username);
	}

	@Override
	public Review show(String username, int rId) {

		return reviewRepo.findByUser_UsernameAndId(username, rId);
	}

	@Override
	public Review create(String username, Review review) {

		if ((review.getUser() != null) && (review.getUser().getUsername() != username)) {
			return null;
		}
		if (review.getUser() == null) {
			User toAssign = userRepo.findByUsername(username);
			if (toAssign == null)
				return null;
			review.setUser(toAssign);
		}

		review.setId(0);
		return reviewRepo.saveAndFlush(review);
	}

	@Override
	public Review update(String username, int rId, Review review) {
		Review toUpdate = reviewRepo.findByUser_UsernameAndId(username, rId);


		if (review.getRestaurant() != null)
			toUpdate.setRestaurant(review.getRestaurant());
		if (review.getReviewText() != null)
			toUpdate.setReviewText(review.getReviewText());
		
		//TODO: update timestamp

		return reviewRepo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean destroy(String username, int rId) {
		
		Review toDelete = reviewRepo.findByUser_UsernameAndId(username, rId);
		if(toDelete == null) {
			return false;
		}
		reviewRepo.delete(toDelete);
		return true;
	}
}
