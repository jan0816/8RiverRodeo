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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.riverrodeo.entities.River;
import com.skilldistillery.riverrodeo.services.FishService;
import com.skilldistillery.riverrodeo.services.RiverService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4280"})
public class RiverController {
	
	@Autowired
	private RiverService riverSvc;

	@GetMapping("rivers")
	public List<River> index(HttpServletRequest req, HttpServletResponse res){
		return riverSvc.listAllRivers();
	}
	
	@GetMapping("rivers/{riverId}")
	public River showById(@PathVariable("riverId") Integer rid, HttpServletResponse response) {
		River river = riverSvc.findById(rid);
		if (river == null) {
			response.setStatus(404);
		}
		return river;
	}
	
	@PostMapping("rivers")
	public River createRiver(@RequestBody River river, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		River createdRiver = riverSvc.createRiver(river, principal.getName());
		if (createdRiver != null) {
			res.setStatus(201);
			StringBuffer reqUrl = req.getRequestURL();
			reqUrl.append("/").append(createdRiver.getId());
			res.setHeader("Location", reqUrl.toString());
		} else {
			res.setStatus(404);
		}
		return createdRiver;
	}
	@PutMapping("rivers/{riverId}")
	public River updateRiver(@PathVariable("riverId") int riverId, @RequestBody River river,
			HttpServletResponse resp, Principal principal) {
		try {
			river = riverSvc.updateRiver(riverId, river, principal.getName());
			if (river == null) {
				resp.setStatus(400);
			}else {
				resp.setStatus(202);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			river = null;
		}
		return river;
	}
	
	@DeleteMapping("rivers/{riverId}")
	public void deleteRiver(@PathVariable("riverId") int riverId, HttpServletResponse response, Principal principal) {
		try {
			if (riverSvc.deleteRiver(riverId, principal.getName())) {
				response.setStatus(204);

			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(409);
		}
	}
}
