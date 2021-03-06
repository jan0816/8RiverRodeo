package com.skilldistillery.riverrodeo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.repositories.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Team findTeamByName(String teamName) {
		return teamRepo.findByName(teamName);
	}

	@Override
	public List<Team> listAllTeams() {
		return teamRepo.findAll();
	}

	@Override
	public Team findById(Integer teamId, String teamname) {
		Team team = null;
		Team loggedInTeam = teamRepo.findByName(teamname);
		Optional<Team> optTeam = teamRepo.findById(teamId);
		if (optTeam.isPresent() && loggedInTeam != null) {
			team = optTeam.get();
		}
		return team;
	}

	@Override
	public Team updateTeam(Integer teamId, Team team, String teamname) {
		Team loggedInTeam = teamRepo.findByName(teamname);
		Optional<Team> optTeam = teamRepo.findById(teamId);
		if (optTeam.isPresent() && loggedInTeam != null) {
			Team dbTeam = optTeam.get();
			if (dbTeam != null) {				
				dbTeam.setName(team.getName());
				String encrypted = encoder.encode(team.getPassword());
				dbTeam.setPassword(encrypted); // only persist encoded password
				dbTeam.setPhoneNumber(team.getPhoneNumber());
				dbTeam.setPictureUrl(team.getPictureUrl());
				dbTeam.setEnabled(team.isEnabled());
				return teamRepo.saveAndFlush(dbTeam);
			}
		}
		return null;
	}

	@Override
	public Boolean changeTeamEnabled(Integer teamId, String teamname) {
		Team loggedInTeam = teamRepo.findByName(teamname);
		Optional<Team> optTeam = teamRepo.findById(teamId);
        if (optTeam.isPresent() && loggedInTeam != null) {
            Team managedTeam = optTeam.get();
            if (managedTeam != null ) {
            	managedTeam.setEnabled(!managedTeam.isEnabled());
            	teamRepo.saveAndFlush(managedTeam);
            	return true;            	
            }
		}
        return false;
	}

	
	@Override
	public Boolean deleteTeam(Integer teamId, String teamname) {
		Team loggedInTeam = teamRepo.findByName(teamname);
		Optional<Team> optTeam = teamRepo.findById(teamId);
        if (optTeam.isPresent() && loggedInTeam != null) {
			Team managedTeam = optTeam.get();
			if (managedTeam != null) {
				teamRepo.deleteById(teamId);
				return true;
			}
		}
		return false;
	}

}
