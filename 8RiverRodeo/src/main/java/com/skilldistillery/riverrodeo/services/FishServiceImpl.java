package com.skilldistillery.riverrodeo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.Fish;
import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.repositories.FishRepository;
import com.skilldistillery.riverrodeo.repositories.TeamRepository;

@Service
public class FishServiceImpl implements FishService {

	@Autowired
	private FishRepository fishRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Override
	public List<Fish> listAllFishes() {
		return fishRepo.findAll();
	}

	@Override
	public Fish findById(Integer fishId) {
		Optional<Fish> optFish = fishRepo.findById(fishId);
		Fish fish = null;
		if (optFish.isPresent()) {
			fish = optFish.get();
		} else {
			return null;
		}
		return fish;
	}
	
	@Override
	public Fish createFish(Fish fish, String username) {
		Team team = teamRepo.findByName(username);
		 if (team != null) {
	            fish.setSizeInCm(fish.getSizeInCm());
	            fish.setPictureUrl(fish.getPictureUrl());;
	            fish.setDayCaught(fish.getDayCaught());
	            fish.setUser(fish.getUser());
	            fish.setRiver(fish.getRiver());
	            fishRepo.saveAndFlush(fish);
	        } else {
	        	fish = null;
	        }
	        return fish;
	    }

	@Override
	public Fish updateFish(Integer fishId, Fish fish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteFish(Integer fishId) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
