package PramodDutta;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreatrUser {
	
	@Test
	public void createMethod() {
		
		RestAssured.baseURI="https://reqres.in/api/users";
		String userData="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}\r\n"
				+ "";
		given().log().all().header("Content-Type", "application/json")
		       .body(userData)
		.when().post()
		 .then().log().all().assertThat().statusCode(201).body("id", is(notNullValue()));        
		          
	
	}

}
