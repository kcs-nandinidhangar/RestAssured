package PramodDutta;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetUser {

	public static void main(String arg[]) {
		
		RestAssured.baseURI="https://reqres.in/api/users";
		given()
		        .queryParam("page", "2")
		        .body("")
	  .when()
	            .get()	 
	  .then()
	           .log().all().assertThat().statusCode(200)
	           .body("page", equalTo(2));
				
	}
}
