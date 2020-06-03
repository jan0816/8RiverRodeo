package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.Team;


public interface TeamService {
	List<Team> listAllTeams();
	Team findById(Integer teamId, String teamname);
	Team updateTeam(Integer teamId, Team team, String teamname);
	Boolean changeTeamEnabled(Integer teamId, String teamname);
	Boolean deleteTeam(Integer teamId, String teamname);
	Team findTeamByName(String teamName);

}
