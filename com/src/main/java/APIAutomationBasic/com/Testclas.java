package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Testclas {

	
	public static void main(String arg[]) {
		
		// post place
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 40,\r\n"
				+ "  \"name\": \"TEST house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 5498\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
	System.out.println(response);
	JsonPath js =new JsonPath(response);
	String placeId=js.get("place_id");
	System.out.println(placeId);

	// Get Method
     
	      given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	      .when().get("maps/api/place/get/json")
	      .then().log().all().assertThat().statusCode(200);
		
	   // Put Method
	     
	      String newAddress="101, main tampa score USA";
	      given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	      .body("{\r\n"
	      		+ "\"place_id\":\""+placeId+"\",\r\n"
	      		+ "\"address\":\""+newAddress+"\",\r\n"
	      		+ "\"key\":\"qaclick123\"\r\n"
	      		+ "}\r\n"
	      		+ "")
	      .when().put("maps/api/place/update/json")
	      .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	      
		 // Get Method
	      
	     String getResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	      .when().get("maps/api/place/get/json")
	      .then().log().all().assertThat().statusCode(200).extract().response().asString();
	      
		System.out.println(getResponse);
		JsonPath js1 =new JsonPath(getResponse);
		String actualAddress=js1.get("address");
		System.out.println(actualAddress);
		
		Assert.assertEquals(newAddress, actualAddress);
		
		   // Delete place
	}
}
