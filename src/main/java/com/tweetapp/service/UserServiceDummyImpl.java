package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tweetapp.dao.UserDummyDAOImpl;
import com.tweetapp.entity.User;

//@Service
public class UserServiceDummyImpl implements UserService{
	
	@Autowired
	private UserDummyDAOImpl userDAOImpl;

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAOImpl.getUsers();
	}

	@Override
	public void saveUser(User user) {
		userDAOImpl.saveUser(user);
	}

	@Override
	public User getUser(String theId) {
		return userDAOImpl.getUser(theId);
	}

	@Override
	public void deleteUser(String theId) {
		userDAOImpl.deleteUser(theId);
		
	}

	@Override
	public List<User> searchUsers(String username) {
		return userDAOImpl.searchUsers(username);
	}

	@Override
	public User registerUser(User user) {
		return userDAOImpl.registerUser(user);
	}

	@Override
	public User processLogin(User user) {
		return userDAOImpl.processLogin(user);
	}

	@Override
	public String forgotPassword(String username) {
		return userDAOImpl.forgotPassword(username);
	}

}
