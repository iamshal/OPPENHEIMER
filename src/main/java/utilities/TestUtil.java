package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.Page;


public class TestUtil extends Page {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "//target//surefire-reports//html//" + screenshotName));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][]  data = new Object[rows - 1][cols];
		
		
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

						
			for (int colNum = 0; colNum < cols; colNum++) {  

				// data[0][0]
				data[rowNum - 2][colNum] =excel.getCellData(sheetName,colNum,rowNum);  	
				}

		}

		return data;

	}

	
@DataProvider(name="dp1")
public Object[][] getData1(Method m) {

	String sheetName = m.getName();
	int rows = excel1.getRowCount(sheetName);
	int cols = excel1.getColumnCount(sheetName);

	Object[][]  data = new Object[rows - 1][cols];
	
	
	for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

					
		for (int colNum = 0; colNum < cols; colNum++) {  

			// data[0][0]
			data[rowNum - 2][colNum] =excel1.getCellData(sheetName,colNum,rowNum);  	
			}

	}

	return data;

}
}
	
	
	
	
	
	
	
	
	
	

	

		
	
	
