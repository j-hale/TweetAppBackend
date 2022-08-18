package com.tweetapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.User;

@Repository
public class UserDummyDAOImpl implements UserDAO {
	


	@Override
	public List<User> getUsers() {
		
		System.out.println("Inside dummy getUsers method");

		List<User> dummyUsers = new ArrayList<User>();
		dummyUsers.add(new User("user1", "Jeck", "Wolnick", "redbot@mo.co", "ps1"));
		dummyUsers.add(new User("user2", "Esta", "Ibenhaus", "greenbot@mo.co", "ps2"));
		dummyUsers.add(new User("user3", "Alteth", "Noit", "yellowbot@mo.co", "ps3"));
		dummyUsers.add(new User("user4", "Grah", "Himingber", "angrybirdbot@mo.co", "ps4"));
		dummyUsers.add(new User("user5", "Remple", "Aslot", "pinkbot@mo.co", "ps5"));

		return dummyUsers;
	}

	@Override
	public void saveUser(User theUser) {
		System.out.println("Inside dummy saveUser method");
	}

	@Override
	public User getUser(String username) {
		System.out.println("Inside dummy getUser method");
		User user = new User("lochieRRR", "Grah", "Himingber", "angrybirdbot@mo.co", "ps4");
		user.setUserId(2);
		return user;
	}

	@Override
	public void deleteUser(String username) {
		System.out.println("Inside dummy deleteUser method");

	}
	
	@Override
	public List<User> searchUsers(String username) {
		List<User> dummyUsers = new ArrayList<User>();
		dummyUsers.add(new User("dummyMatchingUser", "Jeck", "Wolnick", "redbot@mo.co", "ps1"));
		return dummyUsers;
	}
	
	@Override
	public User registerUser(User user) {
		return user;
	}
	
	@Override
	public User processLogin(User user) {
		//implement hashing later
		
		//dummy login check
		if(user.getLoginID().equals("user4") && user.getPassword().equals("ps4")) {
			return new User("user4", "Grah", "Himingber", "angrybirdbot@mo.co", "ps4");
		}
		return null;
	}
	
	@Override
	public String forgotPassword(String username) {
		if (username.equals("user4")) {
			return "ps4";
		}
		return null;
	}

}
