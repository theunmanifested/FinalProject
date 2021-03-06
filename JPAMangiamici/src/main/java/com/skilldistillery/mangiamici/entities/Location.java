package com.skilldistillery.mangiamici.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// FIXME check corresponding User entity/table or if Even Needed
//	@OneToOne/@JoinColumn(name=location_id)
	@JsonIgnore
	@OneToOne(mappedBy = "location")
	private User user;

	private String address;

	private String city;

	private String state;

	private String zip;

	private Double latitude;

	private Double longitude;

	private String phone;

	@Column(name = "is_public")
	private Boolean isPublic = true; 

	// no-arg Constructor
	public Location() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public String toString() {
		return "Location [id=" + id +  ", address=" + address + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude + ", phone=" + phone
				+ ", isPublic=" + isPublic + "]";
	}



	

	



}
