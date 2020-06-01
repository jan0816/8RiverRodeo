package com.skilldistillery.riverrodeo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
