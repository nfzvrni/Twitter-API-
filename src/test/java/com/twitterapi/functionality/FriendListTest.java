package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.utility.IUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FriendListTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"regression"})
	public void getFollowersList() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().get(FOLLOWER_LIST_RESOURCE)
				.then()
				.assertThat().statusCode(200)
				.body("users", notNullValue())
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		IUtilities.getSequence(js, "users.name");
	}
}
