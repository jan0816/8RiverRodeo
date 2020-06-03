package com.skilldistillery.riverrodeo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.entities.User;
import com.skilldistillery.riverrodeo.repositories.TeamRepository;
import com.skilldistillery.riverrodeo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TeamRepository teamRepo;

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
	public User updateUser(Integer userId, User user, String teamname) {
		System.out.println(user);
		Team dbTeam = null;
		if (user.getTeam() != null) {
			System.out.println(user.getTeam().getId());
			Optional<Team> optTeam = teamRepo.findById(user.getTeam().getId());
			if (optTeam.isPresent()) {
				dbTeam = optTeam.get();
			}
		}
		Team loggedInTeam = teamRepo.findByName(teamname);
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent() && loggedInTeam != null) {
			User managedUser = optUser.get();
			if (managedUser != null) {
				managedUser.setFirstName(user.getFirstName());
				managedUser.setLastName(user.getLastName());
				if (user.getFishes() != null) {
					managedUser.setFishes(user.getFishes());					
				}
				if (dbTeam != null) {
					// this grabs the team based on the id passed in on json
					managedUser.setTeam(dbTeam);	
				}else {
					// this grabs the team from the user in the database
					managedUser.setTeam(managedUser.getTeam());
				}
				return userRepo.saveAndFlush(managedUser);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteUser(Integer userId, String teamname) {
		Team loggedInTeam = teamRepo.findByName(teamname);
		Optional<User> optUser = userRepo.findById(userId);
        if (optUser.isPresent() && loggedInTeam != null) {
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
