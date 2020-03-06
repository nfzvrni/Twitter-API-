package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;
import com.twitterapi.resources.IPayLoad;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SendDirectMessageTest extends BaseLoader{

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

  @Test(groups= {"smoke"})
  public void sendDirectMessage() {
	  Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.body(IPayLoad.sendDirectMessage())
				.when().post(SEND_DIRECT_MESSAGE_RESOURCE)
				.then().extract().response();

		JsonPath js = new JsonPath(response.asString());
		//System.out.println(js.prettify());
		//System.out.println("Message Id :"+js.get("event.id"));
		log.info("Message: "+js.get("event.message_create.message_data.text"));
		//1047030063840006149
  }
}
