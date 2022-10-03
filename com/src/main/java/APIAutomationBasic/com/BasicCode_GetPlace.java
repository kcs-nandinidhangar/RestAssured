package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BasicCode_GetPlace {
	
	public static void main(String arg[]) {
		
		// Post Method
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
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
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		// Update Method
		
		String newAddress="200 Main Steet USA";
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
	
	      String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	      .when().get("maps/api/place/get/json")
	      .then().log().all().assertThat().statusCode(200).extract().response().asString();
	      
	      System.out.println(getPlaceResponse);
	      JsonPath js1=new JsonPath(getPlaceResponse);
	     String actualAddress= js1.getString("address");
	     System.out.println(actualAddress);
	     Assert.assertEquals(actualAddress,"ABCDFF");
	      
	     
	     // Delete place
	     
	 /*    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	     .body("{\r\n"
	     		+ "    \"place_id\":\""+placeId+"\"\r\n"
	     		+ "}\r\n"
	     		+ "")
	     .when().delete("maps/api/place/delete/json")
	     .then().log().all().assertThat().statusCode(200).body("status",equalTo("OK"));
	 */    
	}
}
