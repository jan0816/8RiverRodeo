package com.skilldistillery.riverrodeo.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.entities.User;
import com.skilldistillery.riverrodeo.services.AuthService;
import com.skilldistillery.riverrodeo.services.TeamService;
import com.skilldistillery.riverrodeo.services.UserService;



@RestController
@CrossOrigin({ "*", "http://localhost:4280" })
public class AuthController {
	@Autowired
	private AuthService svc;
	
	@Autowired
	private TeamService teamSvc;

	@PostMapping("/register")
	public Team registerTeam(@RequestBody Team team, HttpServletResponse response) {
		if (team == null) {
			response.setStatus(400);
		}
		team = svc.register(team);

		return team;
	}

	@GetMapping("/authenticate")
	public Team authenticate(Principal principal) {
		return teamSvc.findTeamByName(principal.getName());
		//return principal;
	}


}
