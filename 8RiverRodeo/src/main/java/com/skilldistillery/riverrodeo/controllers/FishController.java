package com.skilldistillery.riverrodeo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.riverrodeo.entities.Fish;
import com.skilldistillery.riverrodeo.services.FishService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4280"})
public class FishController {
	
	@Autowired
	private FishService fishSvc;

	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("fishes")
	public List<Fish> index(HttpServletRequest req, HttpServletResponse res){
		return fishSvc.listAllFishes();
	}
	
	@GetMapping("fishes/{fishId}")
	public Fish showById(@PathVariable("fishId") Integer fid, HttpServletResponse response) {
		Fish fish = fishSvc.findById(fid);
		if (fish == null) {
			response.setStatus(404);
		}
		return fish;
	}
	
	@PostMapping("fishes")
	public Fish create(@RequestBody Fish fish, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Fish createdFish = fishSvc.createFish(fish, principal.getName());
		if (createdFish != null) {
			res.setStatus(201);
			StringBuffer reqUrl = req.getRequestURL();
			reqUrl.append("/").append(createdFish.getId());
			res.setHeader("Location", reqUrl.toString());
		} else {
			res.setStatus(404);
		}
		return createdFish;
	}
}
