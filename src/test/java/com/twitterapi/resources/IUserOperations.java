package com.twitterapi.resources;

public interface IUserOperations {

	String DIRECT_MESSAGES_RESOURCE = "/direct_messages/events/list.json";
	String searchTweetsByTimeLineResource = "/statuses/user_timeline.json";
	String BASE_URI = "https://api.twitter.com/1.1";
	String CREATE_TWEET_RESOURCE = "/statuses/update.json";
	String DELETE_MESSAGE_BY_ID = "/direct_messages/events/destroy.json?id=";
	String FOLLOWER_LIST_RESOURCE = "/friends/list.json";
	String LATEST_TWEETS_RESOURCE = "/statuses/home_timeline.json";
	String SEND_DIRECT_MESSAGE_RESOURCE = "/direct_messages/events/new.json";
	String TREND_BY_LOCATION_RECOURCE = "/trends/place.json?id=";

	
	static String deleteTweetById(Long tweetId) {
		return "/statuses/destroy/"+tweetId.toString()+".json";
	}
	
	static String reTweetById(String tweetId) {
		return "statuses/retweet/"+tweetId+".json";
	}
	
	


}
