package com.skilldistillery.riverrodeo.services;

import java.util.List;

import com.skilldistillery.riverrodeo.entities.User;

public interface UserService {
	List<User> listAllUsers();
	User findById(Integer userId);
	User updateUser(Integer userId, User user);
	Boolean deleteUser(Integer userId);

}
