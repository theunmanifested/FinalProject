package com.skilldistillery.mangiamici.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name="img_url")
	private String imgUrl;
	
	// FIXME check corresponding UserBadge entity/table or if Even Needed. Check Json Ignore
	@JsonIgnore
	@OneToMany(mappedBy= "badgeId")
	private List<UserBadge> userBadges;

	
	// no-arg Constructor
	public Badge() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public List<UserBadge> getUserBadges() {
		return userBadges;
	}


	public void setUserBadges(List<UserBadge> userBadges) {
		this.userBadges = userBadges;
	}


	@Override
	public String toString() {
		return "Badge [id=" + id + ", name=" + name + ", description=" + description + ", imgUrl=" + imgUrl
				+ ", userBadges=" + userBadges + "]";
	}
	
	
	
	
	
	
}
