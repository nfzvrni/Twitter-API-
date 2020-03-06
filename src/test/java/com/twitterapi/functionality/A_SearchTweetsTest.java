package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.utility.IUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class A_SearchTweetsTest extends BaseLoader {

	@BeforeTest
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test
	public void searchTweets() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)				
				.queryParam("q", "This is an automated tweet, Created at")
				.queryParam("count", "40")
				/* .queryParam("q", "\"This is an automated tweet\"")*/.when().get(/*"search/tweets.json"*/searchTweetsByTimeLineResource)
				.then()
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		System.out.println(js.prettify());
		IUtilities.getSequence(js, "text");
		idsToDelete =  js.get("id");

	}

}



