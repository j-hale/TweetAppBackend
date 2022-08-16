package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetDummyDAOImpl;
import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

//@Service
public class TweetServiceDummyImpl implements TweetService{
	
	@Autowired
	private TweetDummyDAOImpl tweetDummyDAOImpl;
	
	@Override
	@Transactional
	public List<Tweet> getTweets() {
		return tweetDummyDAOImpl.getTweets();
	}

	@Override
	public List<Tweet> getTweetsByUserID(User user) {
		return tweetDummyDAOImpl.getTweetsByUserID(user);
	}

	@Override
	public Tweet postTweet(User user, Tweet tweet) {
		return tweetDummyDAOImpl.postTweet(user, tweet);
	}

	@Override
	public Tweet updateTweet(User user, long id, Tweet tweet) {
		return tweetDummyDAOImpl.updateTweet(user, id, tweet);
	}

	@Override
	public Tweet deleteTweet(User user, long id) {
		return tweetDummyDAOImpl.deleteTweet(user, id);
	}

	@Override
	public Tweet likeTweet(User user, long id) {
		return tweetDummyDAOImpl.likeTweet(user, id);
	}

	@Override
	public Reply replyToTweet(User user, long id, Reply reply) {
		return tweetDummyDAOImpl.replyToTweet(user, id, reply);
	}

	@Override
	public Tweet getTweet(long id) {
		return tweetDummyDAOImpl.getTweet(id);
	}

	@Override
	public List<Reply> getTweetReplies(long tweetID) {
		// TODO Auto-generated method stub
		return null;
	}

}
