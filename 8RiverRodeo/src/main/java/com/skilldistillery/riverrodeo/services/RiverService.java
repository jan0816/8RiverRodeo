package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.River;

public interface RiverService {
	List<River> listAllRivers();
	River findById(Integer riverId);
	River updateRiver(Integer riverId, River river);
	Boolean deleteRiver(Integer riverId);

}
