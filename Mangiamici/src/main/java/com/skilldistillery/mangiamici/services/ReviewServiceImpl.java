package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.FriendRepository;
import com.skilldistillery.mangiamici.repositories.ReviewRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private FriendRepository friendRepo;

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

		return reviewRepo.findByUser_UsernameAndIdAndEnabledTrue(username, rId);
	}

	@Override
	public Review create(String username, Review review) {

		if ((review.getUser() != null) && (review.getUser().getUsername() != username)) {
			return null;
		}
		if (review.getUser() == null) {
			User toAssign = userRepo.findByUsernameAndEnabledTrue(username);
			if (toAssign == null)
				return null;
			review.setUser(toAssign);
		}

		review.setId(0);
		return reviewRepo.saveAndFlush(review);
	}

	@Override
	public Review update(String username, int rId, Review review) {
		Review toUpdate = reviewRepo.findByUser_UsernameAndIdAndEnabledTrue(username, rId);

		if(toUpdate == null ) {
			System.out.println("To Update does not exist or is not enabled.");
			return null;
		}
		if (review.getRestaurant() != null)
			toUpdate.setRestaurant(review.getRestaurant());
		if (review.getReviewText() != null)
			toUpdate.setReviewText(review.getReviewText());

		return reviewRepo.saveAndFlush(toUpdate);
	}

	@Override
	public Review destroy(String username, int rId) {
		
		Review toDelete = reviewRepo.findByUser_UsernameAndIdAndEnabledTrue(username, rId);
		if(toDelete == null) {
			return null;
		}
		toDelete.setEnabled(false);
		
		return reviewRepo.saveAndFlush(toDelete);
	}

	@Override
	public List<Review> findFriendsReviews(String username) {
		
		
		return reviewRepo.findFriendsReviews(username);
	}
}
