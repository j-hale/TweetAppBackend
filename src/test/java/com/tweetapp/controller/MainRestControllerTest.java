package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.entity.Tweet;
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
		
	}
	
	@Test
	void getTweets_Should_Return_List_Of_Tweets_When_Tweet_Service_Does() {
		mainRestController.setTweetService(tweetService);
		List<Tweet> tweetsReturned = new ArrayList<Tweet>(); 
		//tweetsReturned.add(new Tweet());
		when(tweetService.getTweets()).thenReturn(tweetsReturned);
		assertEquals(tweetsReturned, mainRestController.getTweets(), "Get tweets should return list of tweets when tweet service does");
	}
	
	@Test
	@DisplayName("Simple multiuplication should work")
	void testMultiply() {
		assertEquals(20, mainRestController.mutiplyMethodForTest(4, 5), "Regular Multiplication should work");
	}
	

}
