package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;

public class DeleteMessagesTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndCredentials() {
		init();
	}

	@Test(groups = {"regression"}, dependsOnGroups = {"getMessages"})
	public void deleteMessage() {
		for (Long id : messageIdsToDelete) {
			given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
					.when().delete(DELETE_MESSAGE_BY_ID + String.valueOf(id))
					.then().assertThat().statusCode(204);
			log.info("Deleted message id: " + id);
		}
	}
}
