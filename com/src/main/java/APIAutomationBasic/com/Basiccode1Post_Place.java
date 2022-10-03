package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;

public class Basiccode1Post_Place {
	
public static void main(String arg[]) {
		
		//Validate if add Place API is working as expected 
		// given - all input details
		// when Submit the API - resource, http method
		// then - validate response
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")	
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 40,\r\n"
				+ "  \"name\": \"PQA house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 1234\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)");	
}
}
