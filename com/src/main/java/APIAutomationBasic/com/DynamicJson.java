package APIAutomationBasic.com;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	


	@DataProvider(name="Booksdata")
	public Object[][] getData() {
		
		return new Object[][] {{"Hindi","101"},{"English","102"},{"Computer","105"}};
	}
	
	
	@Test(dataProvider="Booksdata")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().log().all().header("Content-Type","application/json")
		.body(payload.addbook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
         JsonPath js= new JsonPath(res);
         String id=js.get("ID");
         System.out.println(id);
	
	}
}
