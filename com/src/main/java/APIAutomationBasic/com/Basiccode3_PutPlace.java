package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basiccode3_PutPlace {

	public static void main(String arg[]) {

		// Validate if add Place API is working as expected
		// given - all input details
		// when Submit the API - resource, http method
		// then - validate response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 40,\r\n"
				+ "  \"name\": \"PQA house\",\r\n"
				+ "  \"phone_number\": \"(+91) 768 843 6589\",\r\n"
				+ "  \"address\": \"100, Main Steet USA\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js= new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		// update address
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\"29, side layout, cohen 09 USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body( "msg",equalTo("Address successfully updated"));
	}
}