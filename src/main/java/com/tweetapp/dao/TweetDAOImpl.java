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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TweetDAOImpl implements TweetDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Tweet> getTweets() {
		log.info("+++TEST IN DAO METHOD+++");
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Tweet> theQuery = currentSession.createQuery("from Tweet order by tweet_id DESC", Tweet.class);
		List<Tweet> tweets = theQuery.getResultList();
		return tweets;
	}

	@Override
	public List<Tweet> getTweetsByUserID(User user) {
		long userID = user.getUserId();
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Tweet> theQuery = currentSession.createQuery("from Tweet where user_id=:userID order by tweet_id DESC", Tweet.class);
		theQuery.setParameter("userID", userID);
		List<Tweet> tweets = theQuery.getResultList();
		return tweets;
	}

	// need exception for too long tweet
	@Override
	public Tweet postTweet(User user, Tweet tweet) {
		Session currentSession = sessionFactory.getCurrentSession();
		Tweet resultTweet = new Tweet(tweet.getBody());
		resultTweet.setTag(tweet.getTag());
		resultTweet.setUser(user);
		currentSession.saveOrUpdate(resultTweet);
		return resultTweet;
	}

	@Override
	public Tweet updateTweet(User user, long id, Tweet tweet) {
		Session currentSession = sessionFactory.getCurrentSession();
		Tweet tweetToUpdate = getTweet(id);
		if(tweetToUpdate!=null && user.getUserId()==tweetToUpdate.getUser().getUserId()) {
			tweetToUpdate.setBody(tweet.getBody());
			tweetToUpdate.setTag(tweet.getTag());
		}
		currentSession.saveOrUpdate(tweetToUpdate);
		return tweetToUpdate;
	}

	@Override
	public Tweet deleteTweet(User user, long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Tweet tweetToDelete =  getTweet(id);
		if(tweetToDelete != null && tweetToDelete.getUser().getUserId() == user.getUserId()) {
			currentSession.delete(tweetToDelete);
			return tweetToDelete;  
		}
		return null;
	}

	@Override
	public Tweet likeTweet(User user, long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Tweet tweetToLike = getTweet(id);
		if(tweetToLike!=null) {
			tweetToLike.addLike(user);
			currentSession.saveOrUpdate(tweetToLike);
			return tweetToLike;
		}
		return null;
	}

	@Override
	public Reply replyToTweet(User user, long id, Reply reply) {
		Session currentSession = sessionFactory.getCurrentSession();
		Tweet tweetToReplyTo = getTweet(id);
		if(tweetToReplyTo==null) {
			return null;
		}
		Reply replyToAdd = new Reply(reply.getBody());
		replyToAdd.setTag(reply.getTag());
		replyToAdd.setUser(user);
		System.out.println("Does it reach this point?");
		System.out.println(replyToAdd);
		replyToAdd.setTweet(tweetToReplyTo);
		currentSession.saveOrUpdate(replyToAdd);
		return replyToAdd;
	}
	
	@Override
	public Tweet getTweet(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Tweet.class,id);
		
	}
	
	@Override
	public List<Reply> getTweetReplies(long tweetID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Reply> replyQuery = currentSession.createQuery("from Reply where tweet_id=:tweetID", Reply.class);
		replyQuery.setParameter("tweetID", tweetID);
		List<Reply> replies = replyQuery.getResultList();
		return replies;
	}

}
