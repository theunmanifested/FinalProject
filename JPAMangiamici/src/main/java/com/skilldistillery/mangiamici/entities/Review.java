package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	// FIXME check corresponding UserBadge entity/table or if Even Needed
//	@ManyToOne()
//	@JoinColumn(name = "user_id")
//	private int userId;
//
//	// FIXME check corresponding UserBadge entity/table or if Even Needed
//	@ManyToOne()
//	@JoinColumn(name = "restaurant_id")
//	private int restaurantId;
//	
//	@Column(name="review_text")
//	private String reviewText;
//	
//	private boolean enabled;
//	
//	@Column(name = "created_date")
//    @CreationTimestamp
//    private LocalDateTime createdDate;
//	
//    @Column(name = "updated_date")
//    @UpdateTimestamp
//    private LocalDateTime updatedDate;
//    
//    @Column(name="is_public")
//    private boolean isPublic;
//
//    
//    // no-arg Constructor
//	public Review() {
//		super();
//	}
//
//
//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//	public int getUserId() {
//		return userId;
//	}
//
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//
//	public int getRestaurantId() {
//		return restaurantId;
//	}
//
//
//	public void setRestaurantId(int restaurantId) {
//		this.restaurantId = restaurantId;
//	}
//
//
//	public String getReviewText() {
//		return reviewText;
//	}
//
//
//	public void setReviewText(String reviewText) {
//		this.reviewText = reviewText;
//	}
//
//
//	public boolean isEnabled() {
//		return enabled;
//	}
//
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//
//
//	public LocalDateTime getCreatedDate() {
//		return createdDate;
//	}
//
//
//	public void setCreatedDate(LocalDateTime createdDate) {
//		this.createdDate = createdDate;
//	}
//
//
//	public LocalDateTime getUpdatedDate() {
//		return updatedDate;
//	}
//
//
//	public void setUpdatedDate(LocalDateTime updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//
//	public boolean isPublic() {
//		return isPublic;
//	}
//
//
//	public void setPublic(boolean isPublic) {
//		this.isPublic = isPublic;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Review [id=" + id + ", userId=" + userId + ", restaurantId=" + restaurantId + ", reviewText="
//				+ reviewText + ", enabled=" + enabled + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
//				+ ", isPublic=" + isPublic + "]";
//	}
    
}
