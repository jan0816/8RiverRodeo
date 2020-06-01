package com.skilldistillery.riverrodeo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.riverrodeo.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	Team findByName(String name);
}
