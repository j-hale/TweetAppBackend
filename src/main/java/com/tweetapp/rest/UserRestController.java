package com.tweetapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetService tweetService;

	// Following API mappings still required:

	// Register as new user
	@CrossOrigin	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		// set id to 0 here when you've implemented IDs
		user.setUserId(0);
		return userService.registerUser(user);
	}

	// Login
	
	@CrossOrigin
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		return userService.processLogin(user);
	}
	

	// Forgot Password - dont really know what to do for this one
	@CrossOrigin
	@GetMapping("/{username}/forgot")
	public String forgotPassword(@PathVariable String username) {
		return userService.forgotPassword(username);
	}

	// Post new tweet
	@CrossOrigin
	@PostMapping("/{username}/add")
	public Tweet postTweet(@PathVariable String username, @RequestBody Tweet tweet) {
		// get userid for that username via user service
		User user = userService.getUser(username);
		if (user != null) {
			return tweetService.postTweet(user, tweet);
		}
		// pass userid into post tweet method
		return null;
	}

	// Update tweet - put mapping (need to learn how)
	@CrossOrigin
	@PutMapping("/{username}/update/{id}")
	public Tweet updateTweet(@PathVariable String username, @PathVariable long id, @RequestBody Tweet tweet) {
		// get userid for that username via user service
		User user = userService.getUser(username);
		if (user != null) {
			return tweetService.updateTweet(user, id, tweet);
		}
		// pass userid into update tweet method
		return null;
	}

	// Delete tweet
	@CrossOrigin
	@DeleteMapping("/{username}/delete/{id}")
	public Tweet deleteTweet(@PathVariable String username, @PathVariable long id) {
		// get userid for that username via user service
		User user = userService.getUser(username);
		if (user != null) {
			return tweetService.deleteTweet(user, id);
		}
		// pass userid into delete tweet method
		return null;
	}

	// Like tweet
	@CrossOrigin
	@PutMapping("/{username}/like/{id}")
	public Tweet likeTweet(@PathVariable String username, @PathVariable long id) {
		// get userid for that username via user service
		User user = userService.getUser(username);
		if (user != null) {
			return tweetService.likeTweet(user, id);
		}
		// pass userid into like tweet method
		return null;
	}

	// Reply to tweet
	@CrossOrigin
	@PostMapping("/{username}/reply/{id}")
	public Reply replyToTweet(@PathVariable String username, @PathVariable long id, @RequestBody Reply reply) {
		// get userid for that username via user service
		User user = userService.getUser(username);
		if (user != null) {
			return tweetService.replyToTweet(user, id, reply);
		}
		// pass userid into reply tweet method
		return null;
	}

	// Get all users
	@CrossOrigin
	@GetMapping("users/all")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	// Get all tweets
	@CrossOrigin
	@GetMapping("all")
	public List<Tweet> getTweets() {
		return tweetService.getTweets();
	}

	// Get all tweets of user
	@CrossOrigin
	@GetMapping("/{username}")
	public List<Tweet> getTweetsByUsername(@PathVariable String username) {
		// get userid for that username via user service
		User user = userService.getUser(username);
		if (user != null) {
			return tweetService.getTweetsByUserID(user);
		}
		// pass userid into get tweets method
		return null;
	}

	// Search by username
	@CrossOrigin
	@GetMapping("/users/search/{username}")
	public List<User> searchUsers(@PathVariable String username) {
		return userService.searchUsers(username);
	}

	// Get by username
	@CrossOrigin
	@GetMapping("/users/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	// Get replies of tweet
	@CrossOrigin
	@GetMapping("/tweet{tweetID}/replies")
	public List<Reply> getTweetReplies(@PathVariable long tweetID){
		return tweetService.getTweetReplies(tweetID);
	}
	
	//GetTweet
	@CrossOrigin
	@GetMapping("/tweet/{tweetID}")
	public Tweet getTweetByID(@PathVariable long tweetID) {
		return tweetService.getTweet(tweetID);
	}

}
