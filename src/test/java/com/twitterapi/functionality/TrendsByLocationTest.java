package com.twitterapi.functionality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitterapi.base.BaseLoader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TrendsByLocationTest extends BaseLoader {

	@BeforeClass
	public void loadUrlAndAuthenticate() {
		init();
	}

	@Test(groups = {"regression"})
	public void getTrendsByLoc() {
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when().get(TREND_BY_LOCATION_RECOURCE + "23424848")
				.then()
				.assertThat().statusCode(200)
				.body("trends", not(empty()))
				.extract().response();

		JsonPath js = new JsonPath(response.asString());
		List<String> trends = Arrays.asList(js.get("trends.name").toString().split(", "));
		log.info("Here are the " + trends.size() + " trending topics by location");
		int i = 1;
		for (String trend : trends) {
			trend = trend.replace("[[", "").replace("]]", "");
			log.info("Trending topic " + i + " by location: " + trend);
			i++;
		}
	}
}
