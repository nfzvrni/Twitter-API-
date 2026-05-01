package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.utility.IUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class A_SearchTweetsTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"regression", "searchTweets"})
	public void searchTweets() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.queryParam("q", "This is an automated tweet, Created at")
				.queryParam("count", "40")
				.when().get(searchTweetsByTimeLineResource)
				.then()
				.assertThat().statusCode(200)
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		IUtilities.getSequence(js, "text");
		tweetIdsToDelete = js.get("id");
		Assert.assertNotNull(tweetIdsToDelete, "Tweet ID list should not be null");
	}
}
