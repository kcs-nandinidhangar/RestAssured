package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AllMethods {
	
	@Test
	public void allDetails() {
		// Add Method
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 35,\r\n"
				+ "  \"name\": \"Essensia house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 5780\",\r\n"
				+ "  \"address\": \"29, India\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP") )
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
		 System.out.println(response);
		 JsonPath js= new JsonPath(response);
		String placeId= js.get("place_id");
		System.out.println("placeId ="+ placeId);
	 
		// Get Method

		
	}

}
