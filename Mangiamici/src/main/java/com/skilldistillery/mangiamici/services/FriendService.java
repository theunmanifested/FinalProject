package com.skilldistillery.mangiamici.services;

import java.util.List;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.Review;

public interface FriendService {

	public List<Friend> findAllRequestsForUser(String username);
	public List<Friend> findAllRequestsByUser(String username);

//    public Friend show(String username, int rId);

    public Friend create(String usernameRequester, String usernameRequested);

    public Friend accept(String usernameRequested, String usernameRequester);

    public Friend destroy(String username, String usernameOtherSide); // param1 is the one who is deleting the "friend relationship."
}
