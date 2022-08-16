package com.tweetapp.service;

import java.util.List;

import com.tweetapp.entity.User;

public interface UserService {
	
	public List<User> getUsers();

	public void saveUser(User user);

	public User getUser(String username);

	public void deleteUser(String username);

	public List<User> searchUsers(String username);

	public User registerUser(User user);

	public User processLogin(User user);

	public String forgotPassword(String username);
}
