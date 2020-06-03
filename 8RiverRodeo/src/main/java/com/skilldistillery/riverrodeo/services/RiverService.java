package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.River;

public interface RiverService {
	List<River> listAllRivers();
	River findById(Integer riverId);
	River createRiver(River river, String username);
	River updateRiver(Integer riverId, River river, String username);
	Boolean deleteRiver(Integer riverId, String username);

}
