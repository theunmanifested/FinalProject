package com.skilldistillery.mangiamici.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="other_user_id")
	private int otherUserId;
	
	public FriendId() {}
	
	public FriendId(int userId, int otherUserId) {
		super();
		this.userId = userId;
		this.otherUserId = otherUserId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOtherUserId() {
		return otherUserId;
	}

	public void setOtherUserId(int otherUserId) {
		this.otherUserId = otherUserId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + otherUserId;
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
		FriendId other = (FriendId) obj;
		if (otherUserId != other.otherUserId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FriendId [userId=" + userId + ", otherUserId=" + otherUserId + "]";
	}
	
}
