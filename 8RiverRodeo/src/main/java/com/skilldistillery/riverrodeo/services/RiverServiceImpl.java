package com.skilldistillery.riverrodeo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.riverrodeo.entities.River;
import com.skilldistillery.riverrodeo.entities.Team;
import com.skilldistillery.riverrodeo.repositories.RiverRepository;
import com.skilldistillery.riverrodeo.repositories.TeamRepository;

@Service
public class RiverServiceImpl implements RiverService {

	@Autowired
	private RiverRepository riverRepo;

	@Autowired
	private TeamRepository teamRepo;

	@Override
	public List<River> listAllRivers() {
		return riverRepo.findAll();
	}

	@Override
	public River findById(Integer riverId) {
		Optional<River> optRiver = riverRepo.findById(riverId);
		River fish = null;
		if (optRiver.isPresent()) {
			fish = optRiver.get();
		} else {
			return null;
		}
		return fish;
	}

	@Override
	public River createRiver(River river, String username) {
		Team team = teamRepo.findByName(username);
		if (team != null) {
			river.setName(river.getName());
			riverRepo.saveAndFlush(river);
		} else {
			river = null;
		}
		return river;
	}

	@Override
	public River updateRiver(Integer riverId, River river, String username) {
		Team team = teamRepo.findByName(username);
		Optional<River> optRiver = riverRepo.findById(riverId);
		if (optRiver.isPresent() && team != null) {
			River updateRiver = optRiver.get();
			updateRiver.setName(river.getName());
			try {
				updateRiver = riverRepo.saveAndFlush(updateRiver);
				return updateRiver;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Boolean deleteRiver(Integer riverId, String username) {
		Optional<River> optRiver = riverRepo.findById(riverId);
		Team currentTeam = teamRepo.findByName(username);
		if (optRiver.isPresent() && currentTeam != null) {
			River managedRiver = optRiver.get();
			if (managedRiver != null) {
				riverRepo.deleteById(riverId);
				return true;
			}
		}
		return false;
	}

}
