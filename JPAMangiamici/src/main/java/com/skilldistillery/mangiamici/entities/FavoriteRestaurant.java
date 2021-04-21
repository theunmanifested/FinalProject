package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="favorite_restaurant")
public class FavoriteRestaurant {

	@EmbeddedId
	private UserRestaurantId id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	@MapsId(value="restaurantId")
	private Restaurant restaurant;
	
	@Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
	
	private String notes;
	
	// no-arg Constructor
	public FavoriteRestaurant() {
		super();
	}

	public UserRestaurantId getId() {
		return id;
	}

	public void setId(UserRestaurantId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "FavoriteRestaurant [id=" + id + ", user=" + user + ", restaurant=" + restaurant + ", createdDate="
				+ createdDate + ", notes=" + notes + "]";
	}
	
	
}
