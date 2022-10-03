package APIAutomationBasic.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sum() {
		JsonPath js = new JsonPath(payload.CoursePrice());
		int sum=0;
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		for(int i=0;i<count;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			System.out.println(price);
			System.out.println(copies);
			
			int amount=price*copies;
			System.out.println("amount"+amount);
			 sum=sum+amount;
		}
		System.out.println(sum);
			
	int getpurchaseAmount =js.get("dashboard.purchaseAmount");
	System.out.println(getpurchaseAmount);
	
	Assert.assertEquals(getpurchaseAmount, sum);
	}
}
