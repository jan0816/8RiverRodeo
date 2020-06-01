package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.Team;


public interface TeamService {
	List<Team> listAllTeams();
	Team findById(Integer teamId);
	Team updateTeam(Integer teamId, Team team);
	Boolean deleteTeam(Integer teamId);
	Team findTeamByName(String teamName);

}
