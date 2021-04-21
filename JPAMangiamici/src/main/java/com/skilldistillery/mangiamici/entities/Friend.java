package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Friend {
	
	@EmbeddedId
	private FriendId id;
	
	@ManyToOne
	@JoinColumn(name="other_user_id")
	@MapsId(value="otherUserId")
	private User otherUser;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	@Column(name = "date_requested")
    private LocalDateTime dateRequested;
	
    @Column(name = "date_approved")
    private LocalDateTime dateApproved;

    // no-arg Constructor
	public Friend() {
		super();
	}


	public FriendId getId() {
		return id;
	}


	public void setId(FriendId id) {
		this.id = id;
	}


	public User getOtherUser() {
		return otherUser;
	}


	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public LocalDateTime getDateRequested() {
		return dateRequested;
	}


	public void setDateRequested(LocalDateTime dateRequested) {
		this.dateRequested = dateRequested;
	}


	public LocalDateTime getDateApproved() {
		return dateApproved;
	}


	public void setDateApproved(LocalDateTime dateApproved) {
		this.dateApproved = dateApproved;
	}
    
}
