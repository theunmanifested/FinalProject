package com.skilldistillery.mangiamici.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.entities.Review;
import com.skilldistillery.mangiamici.entities.User;
import com.skilldistillery.mangiamici.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> index() {
		
		return userRepo.findByEnabledTrue();
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsernameAndEnabledTrue(username);
	}

	@Override
	public User update(String username, User user) {
		
		User toUpdate = userRepo.findByUsernameAndEnabledTrue(username);

		if(toUpdate == null ) {
			System.out.println("To Update does not exist or is not enabled.");
			return null;
		}
		
		// Updates for user.role and user.password are not handled here.
		
		if (user.getPromoOpt() != null)
			toUpdate.setPromoOpt(user.getPromoOpt());
		if (user.getEnabled() != null)
			toUpdate.setEnabled(user.getEnabled());
		if (user.getAboutMe() != null)
			toUpdate.setAboutMe(user.getAboutMe());
		if (user.getFirstName() != null)
			toUpdate.setFirstName(user.getFirstName());
		if (user.getImgUrl() != null)
			toUpdate.setImgUrl(user.getImgUrl());
		if (user.getLastName() != null)
			toUpdate.setLastName(user.getLastName());
		if (user.getLocation() != null)
			toUpdate.setLocation(user.getLocation());
		if (user.getUserStatus() != null)
			toUpdate.setUserStatus(user.getUserStatus());
		
		
		// username special case
		if ((user.getUsername() != null) 
			&& (user.getUsername() != toUpdate.getUsername()) 
			&& (userRepo.findByUsername(username) == null)) 
		{
			toUpdate.setUsername(user.getUsername());
		}

		return userRepo.saveAndFlush(toUpdate);
	}

	@Override
	public User destroy(String username) {
		
		User toDelete = userRepo.findByUsernameAndEnabledTrue(username);
		
		if(toDelete == null) {
			return null;
		}
		toDelete.setEnabled(false);
		
		return userRepo.saveAndFlush(toDelete);
	}
	
}
