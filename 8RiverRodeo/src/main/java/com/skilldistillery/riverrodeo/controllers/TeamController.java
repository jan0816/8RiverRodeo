package com.skilldistillery.riverrodeo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.services.TeamService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4280"})
public class TeamController {
	
	@Autowired
	private TeamService teamSvc;

	@GetMapping("teams")
	public List<Team> listAllTeams() {
		return teamSvc.listAllTeams();
	}
	
	@GetMapping("teams/{teamId}")
	public Team showById(@PathVariable("teamId") int id, HttpServletResponse response, Principal principal) {
		System.out.println(principal.getName());
		Team team = teamSvc.findById(id, principal.getName());
		if (team == null) {
			response.setStatus(404);
		}
		return team;
	}
	
	@PutMapping("teams/{teamId}")
	public Team updateTeam(@PathVariable("teamId") int teamId, @RequestBody Team team,
			HttpServletResponse resp, Principal principal) {
		try {
			team = teamSvc.updateTeam(teamId, team, principal.getName());
			if (team == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			team = null;
		}
		return team;
	}
	
	@DeleteMapping("teams/{teamId}")
	public boolean changeTeamEnabled(HttpServletRequest req, HttpServletResponse res, @PathVariable int teamId, Principal principal) {
		boolean success = teamSvc.changeTeamEnabled(teamId, principal.getName());
		if (success) {
			res.setStatus(200);
		}else {
			res.setStatus(404);
		}
		return success;
	}
	
}
