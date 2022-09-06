package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.entity.Reply;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;
import com.tweetapp.service.TweetServiceImpl;
import com.tweetapp.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MainRestControllerTest {

	MainRestController mainRestController;

	@Mock
	UserServiceImpl userService;

	@Mock
	TweetServiceImpl tweetService;

	@BeforeEach
	void setUp() {
		mainRestController = new MainRestController();
		mainRestController.setUserService(userService);
		mainRestController.setTweetService(tweetService);
	}

	// register user

	@Test
	void registerUser_Should_Return_User_When_User_Service_Does() {
		User userToRegister = new User();
		User userReturned = new User();
		when(userService.registerUser(userToRegister)).thenReturn(userReturned);
		assertEquals(userReturned, mainRestController.registerUser(userToRegister),
				"registerUser should return a user when user service does");
	}

	@Test
	void registerUser_Should_Return_null_When_User_Service_Does() {
		User userToRegister = new User();
		User userReturned = null;
		when(userService.registerUser(userToRegister)).thenReturn(userReturned);
		assertEquals(userReturned, mainRestController.registerUser(userToRegister),
				"registerUser should return null when user service does");
	}

	// login

	@Test
	void login_Should_Return_User_When_User_Service_Does() {
		User userToLogin = new User();
		User userReturned = new User();
		when(userService.processLogin(userToLogin)).thenReturn(userReturned);
		assertEquals(userReturned, mainRestController.login(userToLogin),
				"login should return a user when user service does");
	}

	@Test
	void login_Should_Return_null_When_User_Service_Does() {
		User userToLogin = new User();
		User userReturned = null;
		when(userService.processLogin(userToLogin)).thenReturn(userReturned);
		assertEquals(userReturned, mainRestController.login(userToLogin),
				"login should return null when user service does");
	}

	// forgot password

	@Test
	void forgotPassword_Should_Return_String_When_User_Service_Does() {
		String username = "username";
		String passwordReturned = "password";
		when(userService.forgotPassword(username)).thenReturn(passwordReturned);
		assertEquals(passwordReturned, mainRestController.forgotPassword(username),
				"forgotPassword should return a String when user service does");
	}

	@Test
	void forgotPassword_Should_Return_null_When_User_Service_Does() {
		String username = "username";
		String passwordReturned = null;
		when(userService.forgotPassword(username)).thenReturn(passwordReturned);
		assertEquals(passwordReturned, mainRestController.forgotPassword(username),
				"forgotPassword should return null when user service does");
	}

	// post new tweet

	@Test
	void postTweet_Should_Return_Tweet_When_User_Service_Returns_User_And_Tweet_Service_Returns_Tweet() {
		String username = "username";
		User userReturned = new User();
		Tweet tweetToPost = new Tweet();
		Tweet tweetReturned = new Tweet();
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.postTweet(userReturned, tweetToPost)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.postTweet(username, tweetToPost),
				"postTweet should return a tweet if user service returns a user and tweet service returns a tweet");

	}

	@Test
	void postTweet_Should_Return_null_When_User_Service_Returns_null() {
		String username = "username";
		User userReturned = null;
		Tweet tweetToPost = new Tweet();
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(tweetReturned, mainRestController.postTweet(username, tweetToPost),
				"postTweet should return null if user service returns null");
	}

	@Test
	void postTweet_Should_Return_null_When_User_Service_Returns_User_And_Tweet_Service_Returns_null() {
		String username = "username";
		User userReturned = new User();
		Tweet tweetToPost = new Tweet();
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.postTweet(userReturned, tweetToPost)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.postTweet(username, tweetToPost),
				"postTweet should return null if user service returns a user and tweet service returns null");
	}

	// update tweet

	@Test
	void updateTweet_Should_Return_Tweet_When_User_Service_Returns_User_And_Tweet_Service_Returns_Tweet() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForUpdate = 42;
		Tweet updatedTweet = new Tweet();
		Tweet tweetReturned = new Tweet();
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.updateTweet(userReturned, tweetIdForUpdate, updatedTweet)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.updateTweet(username, tweetIdForUpdate, updatedTweet),
				"updateTweet should return a Tweet when User Service returns a User and Tweet Service returns a Tweet");
	}

	@Test
	void updateTweet_Should_Return_null_When_User_Service_Returns_null() {
		String username = "username";
		User userReturned = null;
		long tweetIdForUpdate = 42;
		Tweet updatedTweet = new Tweet();
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(tweetReturned, mainRestController.updateTweet(username, tweetIdForUpdate, updatedTweet),
				"updateTweet should return null when User Service returns null");
	}

	@Test
	void updateTweet_Should_Return_null_When_User_Service_Returns_User_And_Tweet_Service_Returns_null() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForUpdate = 42;
		Tweet updatedTweet = new Tweet();
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.updateTweet(userReturned, tweetIdForUpdate, updatedTweet)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.updateTweet(username, tweetIdForUpdate, updatedTweet),
				"updateTweet should return null when User Service returns a User and Tweet Service returns null");
	}

	// delete tweet

	@Test
	void deleteTweet_Should_Return_Tweet_When_User_Service_Returns_User_And_Tweet_Service_Returns_Tweet() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForDeletion = 42;
		Tweet tweetReturned = new Tweet();
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.deleteTweet(userReturned, tweetIdForDeletion)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.deleteTweet(username, tweetIdForDeletion),
				"deleteTweet should return a tweet when User Service returns a user and Tweet Service returns a Tweet");
	}

	@Test
	void deleteTweet_Should_Return_null_When_User_Service_Returns_null() {
		String username = "username";
		User userReturned = null;
		long tweetIdForDeletion = 42;
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(tweetReturned, mainRestController.deleteTweet(username, tweetIdForDeletion),
				"deleteTweet should return null when User Service returns null");
	}

	@Test
	void deleteTweet_Should_Return_null_When_User_Service_Returns_User_And_Tweet_Service_Returns_null() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForDeletion = 42;
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.deleteTweet(userReturned, tweetIdForDeletion)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.deleteTweet(username, tweetIdForDeletion),
				"deleteTweet should return null when User Service returns a user and Tweet Service null");
	}

	// like tweet

	@Test
	void likeTweet_Should_Return_Tweet_When_User_Service_Returns_User_And_Tweet_Service_Returns_Tweet() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForLike = 42;
		Tweet tweetReturned = new Tweet();
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.likeTweet(userReturned, tweetIdForLike)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.likeTweet(username, tweetIdForLike),
				"likeTweet should return tweet when user service returns user and tweet service returns tweet");
	}

	@Test
	void likeTweet_Should_Return_null_When_User_Service_Returns_null() {
		String username = "username";
		User userReturned = null;
		long tweetIdForLike = 42;
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(tweetReturned, mainRestController.likeTweet(username, tweetIdForLike),
				"likeTweet should return null when user service returns null");
	}

	@Test
	void likeTweet_Should_Return_null_When_User_Service_Returns_User_And_Tweet_Service_Returns_null() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForLike = 42;
		Tweet tweetReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.likeTweet(userReturned, tweetIdForLike)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.likeTweet(username, tweetIdForLike),
				"likeTweet should return null when user service returns user and tweet service returns null");
	}

	// reply to tweet

	@Test
	void replyToTweet_Should_Return_Tweet_When_User_Service_Returns_User_And_Tweet_Service_Returns_Tweet() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForReply = 42;
		Reply replyToAdd = new Reply();
		Reply replyReturned = new Reply();
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.replyToTweet(userReturned, tweetIdForReply, replyToAdd)).thenReturn(replyReturned);
		assertEquals(replyReturned, mainRestController.replyToTweet(username, tweetIdForReply, replyToAdd),
				"replyToTweet should return a reply when user service returns a user and tweet service returns a reply");
	}

	@Test
	void replyToTweet_Should_Return_null_When_User_Service_Returns_null() {
		String username = "username";
		User userReturned = null;
		long tweetIdForReply = 42;
		Reply replyToAdd = new Reply();
		Reply replyReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(replyReturned, mainRestController.replyToTweet(username, tweetIdForReply, replyToAdd),
				"replyToTweet should return null when user service returns null");
	}

	@Test
	void replyToTweet_Should_Return_null_When_User_Service_Returns_User_And_Tweet_Service_Returns_null() {
		String username = "username";
		User userReturned = new User();
		long tweetIdForReply = 42;
		Reply replyToAdd = new Reply();
		Reply replyReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.replyToTweet(userReturned, tweetIdForReply, replyToAdd)).thenReturn(replyReturned);
		assertEquals(replyReturned, mainRestController.replyToTweet(username, tweetIdForReply, replyToAdd),
				"replyToTweet should return null when user service returns a user and tweet service returns null");
	}

	// get all users

	@Test
	void getUsers_Should_Return_List_Of_Users_When_User_Service_Does() {
		List<User> usersReturned = new ArrayList<User>();
		when(userService.getUsers()).thenReturn(usersReturned);
		assertEquals(usersReturned, mainRestController.getUsers(),
				"getUsers should return list of users when user service does");
	}

	@Test
	void getUsers_Should_Return_null_When_User_Service_returns_null() {
		List<User> usersReturned = null;
		when(userService.getUsers()).thenReturn(usersReturned);
		assertEquals(usersReturned, mainRestController.getUsers(), "getUsers should null when user service does");
	}

	// get all tweets

	@Test
	void getTweets_Should_Return_List_Of_Tweets_When_Tweet_Service_Does() {
		List<Tweet> tweetsReturned = new ArrayList<Tweet>();
		when(tweetService.getTweets()).thenReturn(tweetsReturned);
		assertEquals(tweetsReturned, mainRestController.getTweets(),
				"getTweets should return list of tweets when tweet service does");
	}

	@Test
	void getTweets_Should_Return_null_When_Tweet_Service_Does() {
		List<Tweet> tweetsReturned = null;
		when(tweetService.getTweets()).thenReturn(tweetsReturned);
		assertEquals(tweetsReturned, mainRestController.getTweets(),
				"getTweets should return null when tweet service does");
	}

	// get tweets by username

	@Test
	void getTweetsByUsername_Should_Return_List_Of_Tweets_When_User_Service_Returns_User_And_Tweet_Service_Returns_List_Of_Tweets() {
		String username = "username";
		User userReturned = new User();
		List<Tweet> tweetsReturned = new ArrayList<Tweet>();
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.getTweetsByUserID(userReturned)).thenReturn(tweetsReturned);
		assertEquals(tweetsReturned, mainRestController.getTweetsByUsername(username),
				"getTweetsByUsername Should Return List Of Tweets When User Service Returns User And Tweet Service Returns List Of Tweets");
	}
	
	@Test
	void getTweetsByUsername_Should_Return_null_When_User_Service_Returns_null() {
		String username = "username";
		User userReturned = null;
		List<Tweet> tweetsReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(tweetsReturned, mainRestController.getTweetsByUsername(username),
				"getTweetsByUsername Should Return null When User Service Returns null");
	}
	
	@Test
	void getTweetsByUsername_Should_Return_null_When_User_Service_Returns_User_And_Tweet_Service_Returns_null() {
		String username = "username";
		User userReturned = new User();
		List<Tweet> tweetsReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		when(tweetService.getTweetsByUserID(userReturned)).thenReturn(tweetsReturned);
		assertEquals(tweetsReturned, mainRestController.getTweetsByUsername(username),
				"getTweetsByUsername Should Return null When User Service Returns User And Tweet Service Returns null");
	}

	// search users
	
	@Test
	void searchUsers_Should_Return_List_Of_Users_When_Users_When_User_Service_Does() {
		String username = "username";
		List<User> usersReturned = new ArrayList<User>();
		when(userService.searchUsers(username)).thenReturn(usersReturned);
		assertEquals(usersReturned, mainRestController.searchUsers(username), 
				"searchUsers should return list of users when user service does");
	}
	
	@Test
	void searchUsers_Should_Return_null_When_Users_When_User_Service_Does() {
		String username = "username";
		List<User> usersReturned = null;
		when(userService.searchUsers(username)).thenReturn(usersReturned);
		assertEquals(usersReturned, mainRestController.searchUsers(username), 
				"searchUsers should return null when user service does");
	}

	// get user by username
	
	@Test
	void getUserByUsername_Should_Return_User_When_User_Service_Does() {
		String username = "username";
		User userReturned = new User();
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(userReturned, mainRestController.getUserByUsername(username),
				"getUserByUsername should return a user when user service does");
	}
	
	@Test
	void getUserByUsername_Should_Return_null_When_User_Service_Does() {
		String username = "username";
		User userReturned = null;
		when(userService.getUser(username)).thenReturn(userReturned);
		assertEquals(userReturned, mainRestController.getUserByUsername(username),
				"getUserByUsername should return null when user service does");
	}

	// get replies of tweet
	
	@Test
	void getTweetReplies_Should_Return_List_Of_Replies_When_Tweet_Service_Does() {
		long tweetId = 42;
		List<Reply> repliesReturned = new ArrayList<Reply>();
		when(tweetService.getTweetReplies(tweetId)).thenReturn(repliesReturned);
		assertEquals(repliesReturned, mainRestController.getTweetReplies(tweetId), "getTweetReplies should return list of replies when tweet service does");
	}

	@Test
	void getTweetReplies_Should_Return_null_When_Tweet_Service_Does() {
		long tweetId = 42;
		List<Reply> repliesReturned = null;
		when(tweetService.getTweetReplies(tweetId)).thenReturn(repliesReturned);
		assertEquals(repliesReturned, mainRestController.getTweetReplies(tweetId), "getTweetReplies should return null when tweet service does");
	}
	
	// get tweet by id
	
	@Test
	void getTweetByID_Should_Return_Tweet_When_Tweet_Service_Does() {
		long tweetId = 42;
		Tweet tweetReturned = new Tweet();
		when(tweetService.getTweet(tweetId)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.getTweetByID(tweetId), 
				"getTweetByID should return tweet when tweetService does");
	}
	
	@Test
	void getTweetByID_Should_Return_null_When_Tweet_Service_Does() {
		long tweetId = 42;
		Tweet tweetReturned = null;
		when(tweetService.getTweet(tweetId)).thenReturn(tweetReturned);
		assertEquals(tweetReturned, mainRestController.getTweetByID(tweetId), 
				"getTweetByID should return null when tweetService does");
	}
	
	

}
