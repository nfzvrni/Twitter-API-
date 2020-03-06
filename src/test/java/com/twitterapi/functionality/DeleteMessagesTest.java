package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;

public class DeleteMessagesTest extends BaseLoader{

	@BeforeClass
	public void loadUrlAndCredentials() {
		init();
	}

	@Test
	public void deleteMessage() {

		
		for(int i =0; i<idsToDelete.size();i++) {
			//String id = idsToDelete.get(i).toString();
			given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
					.when().delete(DELETE_MESSAGE_BY_ID+String.valueOf(idsToDelete.get(i)))
					.then().assertThat().statusCode(204).extract().response();

			System.out.println("Deleting message :"+i);
			
		}
		
		
	}

}
