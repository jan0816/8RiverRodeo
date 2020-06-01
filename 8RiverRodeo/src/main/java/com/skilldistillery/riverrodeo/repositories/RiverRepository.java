package com.skilldistillery.riverrodeo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.riverrodeo.entities.River;

public interface RiverRepository extends JpaRepository<River, Integer> {

}
