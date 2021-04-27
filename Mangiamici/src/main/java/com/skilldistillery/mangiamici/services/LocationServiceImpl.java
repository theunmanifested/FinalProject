package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Location;
import com.skilldistillery.mangiamici.entities.Post;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.LocationRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Location> index() {		
		return locationRepo.findAll();
	}

//	@Override
//	public Location showByName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Location create(String username, Location location) {
		User user = userRepo.findByUsername(username);
		location.setUser(user);
		location = locationRepo.saveAndFlush(location);
		return location;	
	}

//	@Override
//	public Location update(String username, int locationId, Location location) {
//		Location managed = locationRepo.findByUser_UsernameAndId(username, locationId);
//		System.out.println("I am managed ............. " + managed);
//		if(managed != null) {
//			managed.setUser(location.getUser());
//			managed.setAddress(location.getAddress());
//			managed.setCity(location.getCity());
//			managed.setState(location.getState());
//			managed.setZip(location.getZip());
//			managed.setLatitude(location.getLatitude());
//			managed.setLongitude(location.getLongitude());
//			managed.setPhone(location.getPhone());
//			managed.setPublic(location.isPublic());
//			locationRepo.saveAndFlush(managed);
//			return managed;
//		}
//		return null;
//	}
//
//	@Override
//	public boolean destroy(String username, int locationId) {
//		boolean deleted = false;
//		Location loc = locationRepo.findByUser_UsernameAndId(username, locationId);
//		if (loc != null) {
//			locationRepo.delete(loc);
//			deleted = true;
//		}
//		return deleted;
//	}
//	
}
