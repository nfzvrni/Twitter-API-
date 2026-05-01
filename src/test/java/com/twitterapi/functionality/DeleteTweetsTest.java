package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IUserOperations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteTweetsTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndCredentials() {
		init();
	}

	@Test(groups = {"regression"}, dependsOnGroups = {"searchTweets"})
	public void deleteTweet() {
		for (Long tweetId : tweetIdsToDelete) {
			Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
					.when().post(IUserOperations.deleteTweetById(tweetId))
					.then()
					.assertThat().statusCode(200)
					.body("text", notNullValue())
					.extract().response();

			JsonPath js = new JsonPath(response.asString());
			log.info("Deleted tweet id: " + tweetId + " | text: " + js.get("text"));
		}
	}
}
