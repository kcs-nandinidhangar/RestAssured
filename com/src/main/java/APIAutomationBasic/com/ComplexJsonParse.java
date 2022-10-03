package APIAutomationBasic.com;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());
	
		//Print No of courses 
		int count = js.getInt("courses.size()");
		System.out.println(count);

		//Print Purchase Amount
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		//Print Title of the first course
		
		String titleFirstCourse=js.getString("courses[0].title");
		System.out.println(titleFirstCourse);
		
		// Print All course titles and their respective Prices
		
		for(int i=0;i<count;i++) {
			String courseTitle=js.get("courses["+i+"].title");
			System.out.println(courseTitle);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		System.out.println("Print no of copies sold by RPA Course");
		
		for(int i=0;i<count;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
	    
			if(courseTitles.equalsIgnoreCase("RPA")) {
				int countCopies=js.get("courses["+i+"].copies");
			System.out.println(countCopies);
			break;
			}		
		}
		
		
		// Verify if Sum of all Course prices matches with Purchase Amount


	}

}
