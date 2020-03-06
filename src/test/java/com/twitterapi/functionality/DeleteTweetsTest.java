package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IUserOperations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class DeleteTweetsTest extends BaseLoader{

	@BeforeClass
	public void loadUrlAndCredentials() {
		init();
	}

	@Test(/*dependsOnMethods= {"SearchTweetsTest.searchTweets"}*/)
	public void deleteTweet() {

		for(Long tweetIdtoDelete:idsToDelete) {

			Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
					.when().post(IUserOperations.deleteTweetById(tweetIdtoDelete))
					.then().extract().response();

			JsonPath js = new JsonPath(response.asString());
			/*System.out.println(js.prettify());*/
			log.info("Deleting the tweet "+js.get("text"));
			log.info("The tweet is deleted, [Truncated = "+js.get("truncated")+"]");
		}


	}

	//@Test
	public void followUser() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().post("friendships/create.json?user_id=92945681")
				.then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		System.out.println(js.prettify());
		//System.out.println(js.get("name"));
		
	}
}
