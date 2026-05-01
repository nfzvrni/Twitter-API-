package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.utility.IUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class A_GetDirectMessagesTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"regression", "getMessages"})
	public void getDirectMessageList() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().get(DIRECT_MESSAGES_RESOURCE)
				.then()
				.assertThat().statusCode(200)
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		IUtilities.getSequence(js, "events.message_create.message_data.text");
		messageIdsToDelete = js.get("events.id");
	}
}
