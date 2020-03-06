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

	@Test
	public void getDirectMessageList() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)	
				//.queryParams("count", "50")
				.when().get(DIRECT_MESSAGES_RESOURCE).then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		System.out.println(js.prettify());		
		IUtilities.getSequence(js, "events.message_create.message_data.text");
		idsToDelete = js.get("events.id");


	}

}
