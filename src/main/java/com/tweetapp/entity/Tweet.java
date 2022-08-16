package com.tweetapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tweet")
public class Tweet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tweet_id")
	private long tweetID;
	
	@Column(name="body")
	private String body;
	
	@Column(name="tag")
	private String tag;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tweet",
			cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	private List<Reply> replies;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="user_tweet_like", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> userLikes;
	
	//need to add property to link with a user
	
	public Tweet() {
		
	}

	public Tweet(String body) {
		this.body = body;
	}



	public long getTweetID() {
		return tweetID;
	}

	public void setTweetID(long tweetID) {
		this.tweetID = tweetID;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	

	@Override
	public String toString() {
		return "Tweet [tweetID=" + tweetID + ", body=" + body + ", tag=" + tag  +"]";
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public List<User> getUserLikes() {
		return userLikes;
	}

	public void setUserLikes(List<User> userLikes) {
		this.userLikes = userLikes;
	}

	//Convenience method
	public void addReply(Reply reply) {
		if(replies==null) {
			replies = new ArrayList<Reply>();
		}
		replies.add(reply);
		reply.setTweet(this);
	}
	
	//Convenience method
	public void addLike(User user) {
		if(userLikes==null) {
			userLikes = new ArrayList<User>();
		}
		if(!userLikePresent(user)) {
			userLikes.add(user);
			user.addLikedTweet(this);
		}
	}

	public boolean userLikePresent(User prospectiveUser) {
		for(User likingUser : userLikes) {
			if(likingUser.getUserId()==prospectiveUser.getUserId()) {
				return true;
			}
		}
		return false;
	}
	
	
}
