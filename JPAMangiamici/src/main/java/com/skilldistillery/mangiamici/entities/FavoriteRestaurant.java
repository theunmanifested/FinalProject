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
	
	
}
