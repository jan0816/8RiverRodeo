package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.Fish;

public interface FishService {
	List<Fish> listAllFishes();
	Fish findById(Integer fishId);
	Fish updateFish(Integer fishId, Fish fish);
	Boolean deleteFish(Integer fishId);

}