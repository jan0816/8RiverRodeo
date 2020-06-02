package com.skilldistillery.riverrodeo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.entities.User;
import com.skilldistillery.riverrodeo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> listAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findById(Integer userId) {
		Optional<User> optUser = userRepo.findById(userId);
		User user = null;
		if (optUser.isPresent()) {
			user = optUser.get();
		}
		return user;
	}

	@Override
	public User updateUser(Integer userId, User user) {		
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			User managedUser = optUser.get();
			if (managedUser != null) {
				managedUser.setFirstName(user.getFirstName());
				managedUser.setFishes(user.getFishes());
				managedUser.setLastName(user.getLastName());
				managedUser.setTeam(user.getTeam());
				return userRepo.saveAndFlush(managedUser);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteUser(Integer userId) {
		Optional<User> optUser = userRepo.findById(userId);
        if (optUser.isPresent()) {
			User managedUser = optUser.get();
			if (managedUser != null) {
				userRepo.deleteById(userId);
				return true;
			}
		}
		return false;
	}

	@Override
	public User createUser(User user, Team team) {
		if (user != null) {			
			user.setTeam(team);
			try {
				return userRepo.saveAndFlush(user);							
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
