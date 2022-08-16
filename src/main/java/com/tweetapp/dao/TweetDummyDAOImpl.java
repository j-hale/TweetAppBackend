package com.tweetapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

@Repository
public class TweetDummyDAOImpl implements TweetDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Tweet> getTweets() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query  ... sort by last name
		Query<Tweet> theQuery = 
				currentSession.createQuery("from Tweet",
											Tweet.class);
		
		// execute query and get result list
		List<Tweet> tweets = theQuery.getResultList();
				
		// return the results		
		return tweets;
	}

	@Override
	public List<Tweet> getTweetsByUserID(User user) {
		Tweet dTweet1 = new Tweet("Body 1 Text Here by "+user.getLoginID());
		Tweet dTweet2 = new Tweet("Body 2 Text Here by "+user.getLoginID());
		List<Tweet> dummyTweets = new ArrayList<Tweet>();
		dummyTweets.add(dTweet1);
		dummyTweets.add(dTweet2);
		return dummyTweets;
	}
	
	@Override
	public Tweet postTweet(User user, Tweet tweet) {
		//id hardcoded here, will be generated in future
		return new Tweet(user.getLoginID()+" writes: " +tweet.getBody());
	}
	
	@Override
	public Tweet updateTweet(User user, long id, Tweet tweet) {
		//implement this 
		return new Tweet("[UPDATED] " + user.getLoginID() + " writes " + tweet.getBody());
	}
	
	@Override
	public Tweet deleteTweet(User user, long id) {
		if(user.getLoginID().equals("lochieRRR") && id==2) {
			return new Tweet("[DELETED] " + user.getLoginID());
		}
		return null;
	}
	
	@Override
	public Tweet likeTweet(User user, long id) {
		if(id==2) {
			return new Tweet("[LIKED BY " + user.getLoginID());
		}
		return null;
	}
	
	@Override
	public Reply replyToTweet(User user, long id, Reply reply) {
		return new Reply("This is a reply to tweet "+ id +" by " +user.getLoginID());
	}

	@Override
	public Tweet getTweet(long id) {
		return null;
	}

	@Override
	public List<Reply> getTweetReplies(long tweetID) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
