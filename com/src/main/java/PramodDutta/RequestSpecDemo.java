package PramodDutta;



import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestSpecDemo {

	  static RequestSpecification getCommonSpec() {
		  
		  RequestSpecBuilder builder =new RequestSpecBuilder();
		  builder.setBaseUri("https://reqres.in");
		  builder.setBasePath("api/users"); 
		  RequestSpecification requestspec = builder.build();
		  return requestspec;
	  }
	  
	  @Test
	  public void test_withoutPara() {
		  
	     	Response response =RestAssured
	     			.given().log().all()
	     			  .spec(getCommonSpec())
	     			  .when()
	     			  .get();
		response.getBody().prettyPrint();	
		
	  }
	  
	  @Test
	  public void test_withPara() {
		  Response response =RestAssured
				  .given().log().all()
				  .spec(getCommonSpec())
				  .queryParam("page", "2")
				  .when().get();
		  
		  response.getBody().prettyPrint();
	  }
}

