package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LatestTweetsTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"smoke"})
	public void getLatestTweets() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.queryParam("count", "5")
				.when().get(LATEST_TWEETS_RESOURCE)
				.then()
				.assertThat().statusCode(200)
				.body("text", not(empty()))
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		List<String> tweets = js.get("text");
		log.info("Here are the latest " + tweets.size() + " tweets.");
		int i = 1;
		for (String text : tweets) {
			log.info("LatestTweet " + i + ": " + text);
			i++;
		}
	}
}
