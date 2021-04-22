package com.skilldistillery.mangiamici.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_status")
public class UserStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	// FIXME possible need or not of a list of users here.
	@JsonIgnore
	@OneToMany(mappedBy = "userStatus")
	private List<User> users;
	
	public UserStatus() {
		super();
	}
	
	//maybe not needed
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

	@Override
	public String toString() {
		return "UserStatus [id=" + id + ", name=" + name + "]";
	}
	
}
