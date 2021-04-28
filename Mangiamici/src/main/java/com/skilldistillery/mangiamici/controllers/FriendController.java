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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.services.FriendService;

@CrossOrigin({ "*", "http://localhost:4290" })
@RequestMapping("api")
@RestController
public class FriendController {

	@Autowired
	private FriendService friendSvc;

	@GetMapping("friends")
	public List<Friend> getFriends(Principal principal, HttpServletRequest req, HttpServletResponse res) {

		return friendSvc.findAllFriendsForUser(principal.getName());
	}

	/*
	 * The authorized /principal user is the requester. The @pathVarible username
	 * corresponds to the requested user.
	 */
	@PostMapping("friend/{username}")
	public Friend sendFriendRequest(@PathVariable String username, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {

		Friend friend = friendSvc.create(principal.getName(), username);

		if (friend != null) {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(friend.getId());
			res.setHeader("Location", url.toString());
		} else {
			res.setStatus(404);
		}
		return friend;
	}

	/*
	 * It doesn't matter who sent the request, the user related to the path
	 * variable, or the user related to the principal - both side will be checked
	 * for being the requester / requested in order to grab the friendship from the
	 * database.
	 */
	@PutMapping("friends/{username}")
	public Friend update(@PathVariable String username, Principal principal, HttpServletResponse resp,
			HttpServletRequest req) {
		
		System.out.println("Update!*****************************************************");

		Friend friend = friendSvc.accept(principal.getName(), username);
		if (friend == null) {
			resp.setStatus(404);
		}

		return friend;
	}

	/*
	 * It doesn't matter who sent the request, the user related to the path
	 * variable, or the user related to the principal - both side will be checked
	 * for being the requester / requested in order to grab the friendship from the
	 * database.
	 */
	@DeleteMapping("friend/{username}")
	public void delete(@PathVariable String username, Principal principal, HttpServletResponse resp) {

		if (friendSvc.destroy(principal.getName(), username) != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

	/*
	 * Get all not accepted friend requests.  In this case the requests SENT to the user.
	 */
	@GetMapping("friends/requests")
	public List<Friend> getRequests(Principal principal, HttpServletResponse resp) {

		List<Friend> requests = friendSvc.getFriendRequests(principal.getName());

		if (requests == null) {
			resp.setStatus(404);
		}

		return requests;
	}
}
