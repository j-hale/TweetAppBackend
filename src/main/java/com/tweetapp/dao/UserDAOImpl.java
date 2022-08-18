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
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query  ... sort by last name
		Query<User> theQuery = 
				currentSession.createQuery("from User",
											User.class);
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();
				
		// return the results		
		return users;
	}

	@Override
	public void saveUser(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public User getUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query =  currentSession.createQuery("from User where login_id=:username");
	    query.setParameter("username", username);
	    List<User> users = query.getResultList();
	    if(users.isEmpty()) {
	    	return null;
	    }
	    return users.get(0);
	}

	@Override
	public void deleteUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete from User where login_id=:username");
		query.setParameter("username", username);
		query.executeUpdate();
	}
	
	@Override
	public List<User> searchUsers(String username) {
		String pattern = "%" + username + "%";
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where login_id like :pattern");
		query.setParameter("pattern", pattern);
		List<User> users = query.getResultList();
		return users;
	}
	
	@Override
	public User registerUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		return user;
	}
	
	@Override
	public User processLogin(User user) {
		//implement hashing later
		String username = user.getLoginID();
		String password = user.getPassword();
		
		User resultUser = getUser(username);
		if(resultUser!=null && resultUser.getPassword().equals(password)) {
			return resultUser;
		}
		return null;
	}
	
	@Override
	public String forgotPassword(String username) {
		
		User resultUser = getUser(username);
		if(resultUser!=null) {
			return resultUser.getPassword();
		}
		return null;
	}

}
