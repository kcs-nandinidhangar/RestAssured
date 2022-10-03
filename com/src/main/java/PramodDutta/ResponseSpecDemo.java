package PramodDutta;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecDemo {

	
	static ResponseSpecification getCommonSpec() {
		
		ResponseSpecBuilder builder =new ResponseSpecBuilder();
		builder.expectStatusCode(200);
		ResponseSpecification responseSpec= builder.build();
		return responseSpec;
	}
	
	@Test
	public void test_with(){
		
		String url="https://reqres.in/api/users?page=2";
		
		RestAssured.given().log().all()
		.when()  // requestSender
		.get(url)  // Response
		.then()  // ValidatableResponse
		.spec(getCommonSpec());
		
	}
	
	@Test
	public void test_without() {
		
		String url="https://reqres.in/api/users";
		
		RestAssured.given().log().all()
		.when()
		.get(url)
		.then()
		.spec(getCommonSpec())
		.body("page", is(anything()));
	}
}
