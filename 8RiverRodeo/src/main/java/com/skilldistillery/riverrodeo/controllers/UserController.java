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
import com.skilldistillery.riverrodeo.entities.User;
import com.skilldistillery.riverrodeo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4280"})
public class UserController {
	
	@Autowired
	private UserService userSvc;

	@GetMapping("users")
	public List<User> listAllUsers() {
		return userSvc.listAllUsers();
	}
	
	@GetMapping("users/{userId}")
	public User showById(@PathVariable("userId") int userId, HttpServletResponse response, Principal principal) {
		User user = userSvc.findById(userId);
		if (user == null) {
			response.setStatus(404);
		}
		return user;
	}
	
	@PutMapping("users/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User user,
			HttpServletResponse resp, Principal principal) {
		try {
			user = userSvc.updateUser(userId, user, principal.getName());
			if (user == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			user = null;
		}
		return user;
	}
	
}
