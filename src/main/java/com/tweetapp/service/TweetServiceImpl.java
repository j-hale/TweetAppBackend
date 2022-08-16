package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetDAOImpl;
import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

@Service
public class TweetServiceImpl implements TweetService{
	
	@Autowired
	private TweetDAOImpl tweetDAOImpl;
	
	@Override
	@Transactional
	public List<Tweet> getTweets() {
		return tweetDAOImpl.getTweets();
	}

	@Override
	@Transactional
	public List<Tweet> getTweetsByUserID(User user) {
		return tweetDAOImpl.getTweetsByUserID(user);
	}

	@Override
	@Transactional
	public Tweet postTweet(User user, Tweet tweet) {
		return tweetDAOImpl.postTweet(user, tweet);
	}

	@Override
	@Transactional
	public Tweet updateTweet(User user, long id, Tweet tweet) {
		return tweetDAOImpl.updateTweet(user, id, tweet);
	}

	@Override
	@Transactional
	public Tweet deleteTweet(User user, long id) {
		return tweetDAOImpl.deleteTweet(user, id);
	}

	@Override
	@Transactional
	public Tweet likeTweet(User user, long id) {
		return tweetDAOImpl.likeTweet(user, id);
	}

	@Override
	@Transactional
	public Reply replyToTweet(User user, long id, Reply reply) {
		return tweetDAOImpl.replyToTweet(user, id, reply);
	}

	@Override
	@Transactional
	public Tweet getTweet(long id) {
		return tweetDAOImpl.getTweet(id);
	}

	@Override
	@Transactional
	public List<Reply> getTweetReplies(long tweetID) {
		return tweetDAOImpl.getTweetReplies(tweetID);
	}

}
