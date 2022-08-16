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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "login_id")
	private String loginID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "user",
			cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Tweet> tweets; 
	
	@OneToMany(mappedBy = "user",
			cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Reply> replies;
	
	//need to work out how to make this fetch type lazy
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="user_tweet_like", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="tweet_id"))
	private List<Tweet> likedTweets;
	
	public User() {
		
	}

	public User(String loginID, String firstName, String lastName, String email, String password) {
		this.loginID = loginID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLoginID() {
		return loginID;
	}
	
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [loginID=" + loginID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	
	
	
	public List<Tweet> getLikedTweets() {
		return likedTweets;
	}

	public void setLikedTweets(List<Tweet> likedTweets) {
		this.likedTweets = likedTweets;
	}

	//add two convenience methods
	public void addReply(Reply reply) {
		if(replies==null) {
			replies = new ArrayList<Reply>();
		}
		replies.add(reply);
		reply.setUser(this);
	}
	
	public void addTweet(Tweet tweet) {
		if(tweets==null) {
			tweets = new ArrayList<Tweet>();
		}
		tweets.add(tweet);
		tweet.setUser(this);
	}
	
	public void addLikedTweet(Tweet tweet) {
		if(likedTweets==null) {
			likedTweets = new ArrayList<Tweet>();
		}
		likedTweets.add(tweet);
	}
	
	
}
