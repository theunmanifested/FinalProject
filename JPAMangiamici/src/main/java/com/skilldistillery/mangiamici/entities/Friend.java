package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// FIXME check corresponding User entity/table or if Even Needed
	@Column(name="other_user_id")
	private int otherUserId;
	
	// FIXME check corresponding User entity/table or if Even Needed
	@Column(name="user_id")
	private int UserId;
	
	@Column(name = "date_requested")
    private LocalDateTime dateRequested;
	
    @Column(name = "date_approved")
    private LocalDateTime dateApproved;

    
    // no-arg Constructor
	public Friend() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOtherUserId() {
		return otherUserId;
	}


	public void setOtherUserId(int otherUserId) {
		this.otherUserId = otherUserId;
	}


	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
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
