package com.twitterapi.utility;

import java.util.List;

import org.apache.logging.log4j.Logger;

import com.twitterapi.base.BaseLoader;

import io.restassured.path.json.JsonPath;

public interface IUtilities {
	
	Logger log = BaseLoader.log;
	
	 static void getSequence(JsonPath js,String valuesParam) {
		List<String> values = js.get(valuesParam);
		int i = 1;
		for(String value:values) {
			log.info("Value "+i+": is "+value);
			i++;
		}		
	}


}
