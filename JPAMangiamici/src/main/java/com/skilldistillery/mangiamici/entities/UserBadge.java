package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_badge")
public class UserBadge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// FIXME check corresponding User entity/table or if Even Needed
	@ManyToOne()
	@JoinColumn(name="user_id")
	private int userId;
	
	// FIXME check corresponding Badge entity/table or if Even Needed
	@ManyToOne()
	@JoinColumn(name="badge_id")
	private int badgeId;
	
	@Column(name = "date_received")
    private LocalDateTime dateReceived;

		
	// no-arg constructor
	public UserBadge() {
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


	public int getBadgeId() {
		return badgeId;
	}


	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}


	public LocalDateTime getDateReceived() {
		return dateReceived;
	}


	public void setDateReceived(LocalDateTime dateReceived) {
		this.dateReceived = dateReceived;
	}


	@Override
	public String toString() {
		return "UserBadge [id=" + id + ", userId=" + userId + ", badgeId=" + badgeId + ", dateReceived=" + dateReceived
				+ "]";
	}
	
	
	
}
