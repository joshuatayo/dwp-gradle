package com.bpdts.dwp.services;

import java.util.Collection;

import com.bpdts.dwp.models.User;

public interface UserService {
	
	Collection<User> getAllUsers();
	
	Collection<User> getAllUsersFromCity(String city);
	
}
