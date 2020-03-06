package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IUserOperations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReTweetTest extends BaseLoader{
	
	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}
	
	@Test
	public void reTweet() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				/*.queryParam("status", "This is my Updated tweet using Twitter API")*/
				//1048446776645181440
				.when().post(IUserOperations.reTweetById("1048446776645181440"))
				.then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		/*System.out.println(js.prettify());*/
		log.info("Re-Tweeted this tweet: "+js.get("text"));
		//System.out.println(js.get("id"));
	}

}
