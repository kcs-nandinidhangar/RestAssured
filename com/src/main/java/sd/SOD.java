package sd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.testng.Assert;

import io.restassured.response.Response;

public class SOD {
	
	private static final ObjectMapper MAPPER=new ObjectMapper();
	
	public static void main(String[] args) throws JsonProcessingException {
		BlogPost bp1= new BlogPost();
		
		bp1.setId(10);
		bp1.setEmail("qa@software.com");
		bp1.setFirstName("Software");
		bp1.setLastName("QA");
		bp1.setAvatar("https://reqres.in/img/faces/10-image.jpg");
		
		String url="https://reqres.in/api/users";
		
		String json= MAPPER.writeValueAsString(bp1);
		
		
		Response response= RestAssured.given().contentType("application/json")
				.log().all().body(json).when().post(url).andReturn();
		Assert.assertEquals(response.getStatusCode(),201,"Http Message");
	}

}
