package com.skilldistillery.mangiamici.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
		
	@OneToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<Review> reviews;
	
	private String phone;
	
	private String email;
	
	@Column(name="website_url")
	private String websiteUrl;
	
	private Boolean enabled = true;
	
	private String categories;
	
	@Column(name="img_url")
	private String imgUrl;
	
	private String name;
	
	private String description;
	
	@Column(name="menu_url")
	private String menuUrl;
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<FavoriteRestaurant> favoriteRestaurants; 

	// no-arg Constructor
	public Restaurant() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public List<FavoriteRestaurant> getFavoriteRestaurants() {
		return favoriteRestaurants;
	}

	public void setFavoriteRestaurants(List<FavoriteRestaurant> favoriteRestaurants) {
		this.favoriteRestaurants = favoriteRestaurants;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", user=" + user + ", location=" + location + ", phone=" + phone + ", email="
				+ email + ", websiteUrl=" + websiteUrl + ", enabled=" + enabled + ", categories=" + categories
				+ ", imgUrl=" + imgUrl + ", name=" + name + ", description=" + description + ", menuUrl=" + menuUrl
				+ "]";
	}
	
	
}
