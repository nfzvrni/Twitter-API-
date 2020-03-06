package com.twitterapi.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.twitterapi.resources.IPayLoad;
import com.twitterapi.resources.IUserOperations;
import com.twitterapi.utility.IUtilities;

import io.restassured.RestAssured;

public class BaseLoader implements IUserOperations, IPayLoad,IUtilities{
	
	public static String consumerKey, consumerSecret, accessToken, secretToken ;
	public static Logger log = LogManager.getLogger(BaseLoader.class.getName());
	public static List<Long> idsToDelete = new ArrayList<Long>();

	private static void loadUrl() {
		log.info("================================================================");
		log.info("Now Executing :"+BaseLoader.class.getName());
		RestAssured.baseURI=BASE_URI;
	}


	private static Map<String,String> LoadOAuthCredentials() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("consumerKey", "CidfwEO4HMjccwWFyjagEzdaY");
		map.put("consumerSecret", "Wyya3PZQ4mFOPsfuLI9x08ggmzuy7ccsxrlFfkpXIIYaLYa0kM");
		map.put("accessToken", "1046334955440095232-qABw0nkbuMJPNqJZQNxTZiR9wWxSrR");
		map.put("secretToken", "KtxtdY7vTumlmiRlnS9dYgS1Win85kfibtYlUj7eNXVfN");
		return map;
	}

	public void init() {
		BaseLoader.loadUrl();
		Map<String, String> oAuth= BaseLoader.LoadOAuthCredentials();
		consumerKey = oAuth.get("consumerKey");
		consumerSecret = oAuth.get("consumerSecret");
		accessToken = oAuth.get("accessToken");
		secretToken = oAuth.get("secretToken");
	}

}
