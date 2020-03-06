package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateTweetTest extends BaseLoader{


	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}


	@Test(groups= {"smoke"})
	public void createTweet() {

		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.queryParam("status", "This is an automated tweet, Created at "+new Date().getMinutes()+"th minute.")
				.when().post(CREATE_TWEET_RESOURCE)
				.then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		//System.out.println(js.prettify());
		log.info("Created this tweet: "+js.get("text"));
		//System.out.println(js.get("id"));

	}
}
