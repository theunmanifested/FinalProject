package com.skilldistillery.mangiamici.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRestaurantId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="restaurant_id")
	private int restaurantId;

	public UserRestaurantId() {
		super();
	}	

	public UserRestaurantId(int userId, int restaurantId) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + restaurantId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRestaurantId other = (UserRestaurantId) obj;
		if (restaurantId != other.restaurantId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRestaurantId [userId=" + userId + ", restaurantId=" + restaurantId + "]";
	}
	
	
	
}
