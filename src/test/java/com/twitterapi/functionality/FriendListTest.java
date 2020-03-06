package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.utility.IUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FriendListTest extends BaseLoader{

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups= {"regression"})
	public void getFollowersList() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().get(FOLLOWER_LIST_RESOURCE)
				//followers or friends
				.then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		IUtilities.getSequence(js, "users.name");
		//System.out.println(js.get("users.name"));
		
	}

}
