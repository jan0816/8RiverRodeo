package com.skilldistillery.riverrodeo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.riverrodeo.entities.Fish;

public interface FishRepository extends JpaRepository<Fish, Integer> {

}
