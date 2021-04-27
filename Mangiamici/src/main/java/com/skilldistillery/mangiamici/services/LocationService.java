package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.Location;

public interface LocationService {

	List<Location> index();
	
//	Location showByName(String name);
	
//	List<Location> showBySearch(String cat);
	
	Location create(String username, Location location);
	
//	Location update(String username, int locationId, Location location);
//	
//	boolean destroy(String username, int locationId);
	
}
