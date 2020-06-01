package com.skilldistillery.riverrodeo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.Fish;
import com.skilldistillery.riverrodeo.repositories.FishRepository;

@Service
public class FishServiceImpl implements FishService {

	@Autowired
	private FishRepository fishRepo;
	
	@Override
	public List<Fish> listAllFishes() {
		return fishRepo.findAll();
	}

	@Override
	public Fish findById(Integer fishId) {
		// TODO Auto-generated method stub
		return null;
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
