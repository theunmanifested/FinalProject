package com.skilldistillery.mangiamici.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Friend {

	@ManyToOne
	@JoinColumn(name="restaurant_id")
	@MapsId(value="restaurantId")
	private User otherUser;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	@MapsId(value="restaurantId")
	private User user;
	
	@Column(name = "date_requested")
    private LocalDateTime dateRequested;
	
    @Column(name = "date_approved")
    private LocalDateTime dateApproved;

    // no-arg Constructor
	public Friend() {
		super();
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
