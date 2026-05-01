package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IPayLoad;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SendDirectMessageTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"smoke"})
	public void sendDirectMessage() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.body(IPayLoad.sendDirectMessage())
				.when().post(SEND_DIRECT_MESSAGE_RESOURCE)
				.then()
				.assertThat().statusCode(200)
				.body("event.id", notNullValue())
				.body("event.message_create.message_data.text", notNullValue())
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		log.info("Sent message: " + js.get("event.message_create.message_data.text"));
	}
}
