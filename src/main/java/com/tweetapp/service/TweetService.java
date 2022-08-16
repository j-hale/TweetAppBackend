package com.tweetapp.service;

import java.util.List;

import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

public interface TweetService {

	public List<Tweet> getTweets();

	public List<Tweet> getTweetsByUserID(User user);

	public Tweet postTweet(User user, Tweet tweet);

	public Tweet updateTweet(User user, long id, Tweet tweet);

	public Tweet deleteTweet(User user, long id);

	public Tweet likeTweet(User user, long id);

	public Reply replyToTweet(User user, long id, Reply reply);
	
	public Tweet getTweet(long id);

	public List<Reply> getTweetReplies(long tweetID);


}
