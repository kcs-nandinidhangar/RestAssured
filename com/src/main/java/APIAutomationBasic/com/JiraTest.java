package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest {

	
	@Test
	public void addComment() {
		RestAssured.baseURI="http://localhost:8080";
	
		//Login Scenario - 
		SessionFilter session= new SessionFilter();
		
	String response= given().log().all().header("Content-Type","application/json")
		.body("{ \"username\": \"nandini.dhangar\", \"password\": \"jIRA@123\" }").filter(session)
		.when().post("rest/auth/1/session")
		.then().log().all().extract().response().asString();
			
	//  Add Comment	
		
		given().log().all().pathParam("key", "10004").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \"This is my first comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Adminis	trators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201);
		
		// Add attachment
		
		given().header("X-Atlassian-Token", "no-check").filter(session)
		.pathParam("key", "10004").header("Content-Type","multipart/form-data")
		.multiPart("file", new File("Jira.txt"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		// Get Issue
		
		
		String getIssueDetails=given().filter(session).pathParam("key", "10004")
				.queryParam("fields", "comment")
		.log().all().when().get("rest/api/2/issue/{key}")
		.then().log().all().extract().response().asString();
		
		System.out.println(getIssueDetails);
		
		}
}
