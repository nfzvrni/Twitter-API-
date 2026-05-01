package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IUserOperations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReTweetTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"smoke"})
	public void reTweet() {
		// Configure via -Dtwitter.retweetId=<id> or TWITTER_RETWEET_ID env var
		String tweetId = System.getenv("TWITTER_RETWEET_ID");
		if (tweetId == null || tweetId.isEmpty()) {
			tweetId = System.getProperty("twitter.retweetId", "1048446776645181440");
		}

		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().post(IUserOperations.reTweetById(tweetId))
				.then()
				.assertThat().statusCode(200)
				.body("text", notNullValue())
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		log.info("Re-Tweeted: " + js.get("text"));
	}
}
