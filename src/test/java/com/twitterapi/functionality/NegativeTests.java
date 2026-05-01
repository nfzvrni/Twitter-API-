package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IUserOperations;

public class NegativeTests extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"regression"})
	public void getDirectMessagesWithInvalidCredentials() {
		given()
				.auth().oauth("invalid_key", "invalid_secret", "invalid_token", "invalid_token_secret")
				.when().get(DIRECT_MESSAGES_RESOURCE)
				.then()
				.assertThat().statusCode(401);
	}

	@Test(groups = {"regression"})
	public void createTweetWithoutStatusParam() {
		// POST to create-tweet without the required 'status' param -> 403
		given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().post(CREATE_TWEET_RESOURCE)
				.then()
				.assertThat().statusCode(403);
	}

	@Test(groups = {"regression"})
	public void deleteNonExistentTweet() {
		// Tweet ID 1 has not existed for years -> 404
		given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().post(IUserOperations.deleteTweetById(1L))
				.then()
				.assertThat().statusCode(404);
	}
}
