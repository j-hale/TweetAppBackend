package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.entity.User;


public interface UserDAO {
	
	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(String theId);

	public void deleteUser(String theId);

	List<User> searchUsers(String username);

	User registerUser(User user);

	User processLogin(User user);

	String forgotPassword(String username);

}
