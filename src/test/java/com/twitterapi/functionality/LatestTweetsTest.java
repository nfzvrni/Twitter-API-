package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LatestTweetsTest extends BaseLoader{

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test
	public void getLatestTweets() {

		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.queryParam("count", "5").when().get(LATEST_TWEETS_RESOURCE)
				.then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		//System.out.println(js.prettify());
		log.info("Here are the latest "+js.get("text.size()")+" tweets.");
		List<String> tweets = js.get("text");
		int i = 1;
		for(String text:tweets) {
			log.info("LatestTweet "+i+":"+text);
			i++;
		}		
		//System.out.println(js.get("id"));
		
	}
}
