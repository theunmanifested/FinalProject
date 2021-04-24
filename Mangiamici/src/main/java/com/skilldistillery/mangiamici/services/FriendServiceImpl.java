package com.skilldistillery.mangiamici.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Friend;
import com.skilldistillery.mangiamici.entities.FriendId;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.FriendRepository;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendRepository friendRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Friend> findAllRequestsForUser(String username) {

		return friendRepo.findByOtherUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrueAndApprovedFalse(username);
	}

	@Override
	public List<Friend> findAllRequestsByUser(String username) {

		return friendRepo.findByUser_UsernameAndUser_EnabledTrueAndOtherUser_EnabledTrueAndApprovedFalse(username);
	}

	@Override
	public Friend create(String usernameRequester, String usernameRequested) {

		User requester = userRepo.findByUsernameAndEnabledTrue(usernameRequester);
		User requested = userRepo.findByUsernameAndEnabledTrue(usernameRequested);

		if (requester == null || requested == null) {
			return null;
		}

		Friend newFriendship = new Friend(requester, requested);
		FriendId fId = new FriendId();
		fId.setUserId(requester.getId());
		fId.setOtherUserId(requested.getId());

		newFriendship.setId(fId);

		System.out.println(newFriendship);

		return friendRepo.saveAndFlush(newFriendship);
	}

	@Override
	public Friend accept(String usernameRequester, String usernameRequested) {

		Friend f = friendRepo.findByUser_UsernameAndUser_EnabledTrueAndOtherUser_UsernameAndOtherUser_EnabledTrue(
				usernameRequester, usernameRequested);

		if (f == null) {

			f = friendRepo.findByUser_UsernameAndUser_EnabledTrueAndOtherUser_UsernameAndOtherUser_EnabledTrue(
					usernameRequested, usernameRequester);

			if (f == null)
				return null;
		}

		f.setApproved(true);
		f.setDateApproved(LocalDateTime.now());

		return friendRepo.saveAndFlush(f);
	}

	@Override
	public Friend destroy(String usernameRequester, String usernameRequested) {

		Friend toDelete = friendRepo.findByUser_UsernameAndOtherUser_Username(usernameRequester, usernameRequested);

		System.out.println("Check point 1 *********** " + toDelete + " **********************************************************************************************************************************");
		if (toDelete == null) {
			
			// then check for the inverse relationship.
			toDelete = friendRepo.findByUser_UsernameAndUser_EnabledTrueAndOtherUser_UsernameAndOtherUser_EnabledTrue(
					usernameRequested, usernameRequester);
			System.out.println("Check point 2 *********** " + toDelete + " **********************************************************************************************************************************");
			if (toDelete == null)
				return null;
		}

		friendRepo.deleteById(toDelete.getId());

		return friendRepo.saveAndFlush(toDelete);
	}
}
