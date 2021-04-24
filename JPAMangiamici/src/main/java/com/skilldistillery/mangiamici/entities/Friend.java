package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Friend {
	
	@EmbeddedId
	private FriendId id;
	
	@ManyToOne
	@JoinColumn(name="other_user_id")
	@MapsId(value="otherUserId")
	private User otherUser;								// the requested in the friend relationship
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;									// the requester of the friend relationship
	
	private Boolean approved = false;
	
	@CreationTimestamp
	@Column(name = "date_requested")
    private LocalDateTime dateRequested;
	
    @Column(name = "date_approved")
    private LocalDateTime dateApproved;

    // no-arg Constructor
	public Friend() {
		super();
	}


	public Friend(User user, User otherUser) {  
		super();
		this.otherUser = otherUser;
		this.user = user;
		this.approved = false; // this constructor is used for friend requests.
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

	
	public Boolean getApproved() {
		return approved;
	}


	public void setApproved(Boolean approved) {
		this.approved = approved;
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


	@Override
	public String toString() {
		return "Friend [id=" + id + ", otherUser=" + otherUser + ", user=" + user + ", approved=" + approved
				+ ", dateRequested=" + dateRequested + ", dateApproved=" + dateApproved + "]";
	}
    
	
	
}
