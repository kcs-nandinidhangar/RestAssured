package PramodDutta;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetMethod {

	@BeforeSuite
	public void setUp() {
		System.out.println("setup");
	}
	
	@Test
	public void get_Req() {
		RestAssured.baseURI="https://reqres.in/api/users?page=2";
		given().
		when().get().
		then().log().all().statusCode(200);
		
	}
	
	
	@AfterSuite
	public void tearDown() {
		
		System.out.println("tear down");
	}
}
