package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
public class Basiccode {
	
	public static void main(String arg[]) throws IOException {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\nandini.dhangar\\eclipse-workspace\\com\\src\\main\\java\\files\\addPlace.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
	}

}
