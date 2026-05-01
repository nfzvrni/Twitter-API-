package com.twitterapi.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.twitterapi.resources.IPayLoad;
import com.twitterapi.resources.IUserOperations;
import com.twitterapi.utility.IUtilities;

import io.restassured.RestAssured;

public class BaseLoader implements IUserOperations, IPayLoad, IUtilities {

	public static String consumerKey, consumerSecret, accessToken, secretToken;
	public static Logger log = LogManager.getLogger(BaseLoader.class.getName());

	// Separate lists to avoid cross-test pollution between tweet and message flows
	public static List<Long> tweetIdsToDelete = new ArrayList<>();
	public static List<Long> messageIdsToDelete = new ArrayList<>();

	private static void loadUrl() {
		log.info("================================================================");
		log.info("Now Executing: " + BaseLoader.class.getName());
		RestAssured.baseURI = BASE_URI;
	}

	/**
	 * Reads from environment variable first, then falls back to a JVM system property.
	 * Set TWITTER_* env vars or pass -Dtwitter.* to the JVM to supply credentials
	 * without committing them to source control.
	 */
	private static String getCredential(String envVar, String sysProp) {
		String value = System.getenv(envVar);
		if (value != null && !value.isEmpty()) return value;
		return System.getProperty(sysProp, "");
	}

	public void init() {
		BaseLoader.loadUrl();
		consumerKey    = getCredential("TWITTER_CONSUMER_KEY",    "twitter.consumerKey");
		consumerSecret = getCredential("TWITTER_CONSUMER_SECRET", "twitter.consumerSecret");
		accessToken    = getCredential("TWITTER_ACCESS_TOKEN",    "twitter.accessToken");
		secretToken    = getCredential("TWITTER_SECRET_TOKEN",    "twitter.secretToken");
	}
}
