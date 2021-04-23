package com.skilldistillery.mangiamici.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.services.FriendService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class FriendController {

	@Autowired
	private FriendService friendSvc;
	
	/*
	 * The authorized /principal user is the requester.  The @pathVarible username corresponds to the requested user.
	 */
	@PostMapping("friend/{username}")
	public Friend sendFriendRequest(@PathVariable String username, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
		Friend friend = friendSvc.create(principal.getName() , username);
		
		if (friend != null) {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(friend.getId());
			res.setHeader("Location", url.toString());
		}
		else {
			res.setStatus(404);
		}
		return friend;
	}
	
	// _________________________________________________________  Copy paste no work done yet
	@PutMapping("friend/{username}")
	public Friend update(@PathVariable String username, Principal principal, HttpServletResponse resp, HttpServletRequest req) {
		
		Friend friend = friendSvc.accept(principal.getName(), username);
		if(friend == null) {
			resp.setStatus(404);
		}
		
		return friend;
	}
	
//	@DeleteMapping("reviews/{rId}")
//	public void deleteRunForUser(@PathVariable Integer rId, Principal principal, HttpServletResponse resp) {
//		
//		if (svc.destroy(principal.getName(), rId) != null) {
//			resp.setStatus(204);
//		}
//		else {
//			resp.setStatus(404);
//		}
//	}
}
