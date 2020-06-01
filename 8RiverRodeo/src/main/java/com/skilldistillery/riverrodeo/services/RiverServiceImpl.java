package com.skilldistillery.riverrodeo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.River;
import com.skilldistillery.riverrodeo.repositories.RiverRepository;

@Service
public class RiverServiceImpl implements RiverService {

	@Autowired
	private RiverRepository riverRepo;
	
	@Override
	public List<River> listAllRivers() {
		return riverRepo.findAll();
	}

	@Override
	public River findById(Integer riverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public River updateRiver(Integer riverId, River river) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteRiver(Integer riverId) {
		// TODO Auto-generated method stub
		return null;
	}

}
