package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.Fish;

public interface FishService {
	List<Fish> listAllFishes();
	Fish findById(Integer fishId);
	Fish createFish(Fish fish, String username);
	Fish updateFish(Integer fishId, Fish fish, String username);
	Boolean deleteFish(Integer fishId, String username);
}
