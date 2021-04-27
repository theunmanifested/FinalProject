package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Location;
import com.skilldistillery.mangiamici.entities.Post;
import com.skilldistillery.mangiamici.services.LocationService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class LocationController {

	@Autowired
	private LocationService locationSvc;

	@GetMapping("pingLoc")
	public String ping() {
		return "pongLoc";
	}

	// GET all restaurants
	@GetMapping("locations")
	public List<Location> index() {
		return locationSvc.index();
	}

	@PostMapping("locations")
	public Location create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Location location) {
		try {
			location = locationSvc.create(principal.getName(), location);
		} catch (Exception e) {
			System.err.println(e);
			location = null;
		}
		return location;
	}
	
	@PutMapping("locations/{locationId}")
	public Location update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int locationId, @RequestBody Location location) {
		location = locationSvc.update(principal.getName(), locationId, location); 
		if (location == null) {
			res.setStatus(400);
		}
		return location;
	}
	
//  DELETE restaurants/{restaurantId}
	@DeleteMapping("locations/{locationId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int locationId) {
		if(locationSvc.destroy(principal.getName(),locationId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
		
	}

}
