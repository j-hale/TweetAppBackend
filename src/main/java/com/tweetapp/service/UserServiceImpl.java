package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UserDAOImpl;
import com.tweetapp.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAOImpl userDAOImpl;

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAOImpl.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAOImpl.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(String theId) {
		return userDAOImpl.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(String theId) {
		userDAOImpl.deleteUser(theId);
		
	}

	@Override
	@Transactional
	public List<User> searchUsers(String username) {
		return userDAOImpl.searchUsers(username);
	}

	@Override
	@Transactional
	public User registerUser(User user) {
		return userDAOImpl.registerUser(user);
	}

	@Override
	@Transactional
	public User processLogin(User user) {
		return userDAOImpl.processLogin(user);
	}

	@Override
	@Transactional
	public String forgotPassword(String username) {
		return userDAOImpl.forgotPassword(username);
	}

}
