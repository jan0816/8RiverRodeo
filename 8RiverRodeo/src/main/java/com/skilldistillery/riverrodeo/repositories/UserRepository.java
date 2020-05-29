package com.skilldistillery.riverrodeo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.riverrodeo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
