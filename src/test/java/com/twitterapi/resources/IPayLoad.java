package com.twitterapi.resources;

public interface IPayLoad {
	
	static String sendDirectMessage() {
		String body = "{\"event\": {\"type\": \"message_create\", \"message_create\": {\"target\": "
				+ "{\"recipient_id\": \"1047019521805103105\"}, \"message_data\": "
				+ "{\"text\": \"Hello Shubham! We are running this using Maven\"}}}}";
		
		return body;
	}
	
	

}
