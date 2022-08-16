package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

public interface TweetDAO {
	
	 public List<Tweet> getTweets();
	 
	 public List<Tweet> getTweetsByUserID(User user);

	Tweet postTweet(User user, Tweet tweet);

	Tweet updateTweet(User user, long id, Tweet tweet);

	Tweet deleteTweet(User user, long id);

	Tweet likeTweet(User user, long id);

	Reply replyToTweet(User user, long id, Reply reply);

	Tweet getTweet(long id);

	List<Reply> getTweetReplies(long tweetID);

}
