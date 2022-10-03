package ExcelReader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataRead {

	String sheetName = "Flights";
	
	@DataProvider
	public Object[][] getCRMTestData() throws Exception {
		System.out.println("searchFlightTestData 1");
		Object data[][] = ExcelUtil.getTestData(sheetName);
		return data;
	}
	
	@Test (dataProvider = "getCRMTestData")
	public void validateFlightSearch(String from, String to, String adults, String children, String infants) {
		System.out.println("from - " + from);
		System.out.println("to - " + to);
		System.out.println("adults - " + adults);
		System.out.println("children  - " + children);
		System.out.println("infants - " + infants);
	}
}
