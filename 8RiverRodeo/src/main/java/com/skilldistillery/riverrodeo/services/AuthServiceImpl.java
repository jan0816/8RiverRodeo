package com.skilldistillery.riverrodeo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.repositories.TeamRepository;
import com.skilldistillery.riverrodeo.repositories.UserRepository;


@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Override
	public Team register(Team team) {
		Team dbTeam = null;
		String encrypted = encoder.encode(team.getPassword());
		team.setPassword(encrypted); // only persist encoded password
		team.setRole("user");
		dbTeam = teamRepo.saveAndFlush(team);
		userSvc.createUser(team.getTeamMembers().get(0), dbTeam);
		userSvc.createUser(team.getTeamMembers().get(1), dbTeam);
		return team;
	}

}
