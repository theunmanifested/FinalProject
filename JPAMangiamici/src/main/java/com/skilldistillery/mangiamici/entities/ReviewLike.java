package com.skilldistillery.mangiamici.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="review_like")
public class ReviewLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// FIXME check corresponding User entity/table or if Even Needed
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private int userId;

	// FIXME check corresponding UserBadge entity/table or if Even Needed
	@ManyToOne()
	@JoinColumn(name = "review_id")
	private int reviewId;
	
	private boolean liked;
	
	private boolean enabled;

	
	// no-arg Constructors
	public ReviewLike() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getReviewId() {
		return reviewId;
	}


	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}


	public boolean isLiked() {
		return liked;
	}


	public void setLiked(boolean liked) {
		this.liked = liked;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "ReviewLike [id=" + id + ", userId=" + userId + ", reviewId=" + reviewId + ", liked=" + liked
				+ ", enabled=" + enabled + "]";
	}
	
	
	
	
	
}
