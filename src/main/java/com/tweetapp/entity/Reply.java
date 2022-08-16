package com.tweetapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "reply")
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private long replyId;
	
	@Column(name ="body")
	private String body;
	
	@Column(name ="tag")
	private String tag;
	
	@JsonIgnore
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="tweet_id")
	private Tweet tweet;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	public Reply() {
		
	}
	
	
	public Reply(String body) {
		this.body = body;
	}



	public long getReplyId() {
		return replyId;
	}

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}



	public Tweet getTweet() {
		return tweet;
	}



	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", body=" + body + ", tag=" + tag + ", tweet=" + tweet + ", user=" + user
				+ "]";
	}
	
	
	
	
	
}
