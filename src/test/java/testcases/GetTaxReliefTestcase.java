package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import org.json.*;

import API.GetTaxRelief;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTaxReliefTestcase extends BaseTest {
	@Test
	public void getTax() throws FileNotFoundException {
		String dollarTest, reliefTest;
		dollarTest = reliefTest = "true";
		Response response = GetTaxRelief.getTax();

		//story number 4 - AC1, AC2
		JsonPath json = response.jsonPath();
		List<String> natidFromDatabase = json.getList("natid");


		for (String id : natidFromDatabase) {
			if (id.substring(4).equals("$$$$$$$")) {
				dollarTest = "true";
			} else {
				dollarTest = "false";
			}
		}
		//story number 4 - AC3, AC4, AC5, AC6
		List<String> reliefFromDatabase = json.getList("relief");
		
		//putting expected values from excel to Json object
		JSONObject expectedValueJsonObj = new JSONObject();
		String k;
		Float valueFromDatabase, expectedValue;
		Scanner scanner = new Scanner(new File("C:\\Users\\iamsh\\Desktop\\calculateRelief.csv"));
		while (scanner.hasNext()) {
			k = scanner.next();
			expectedValueJsonObj.put(k.split(",")[0].substring(0, 3), k.split(",")[1]);
		}
		scanner.close();

		Integer len = natidFromDatabase.size();
		for (int i = 0; i < len; i++) {
			if (natidFromDatabase.get(i).startsWith("10")) {
				valueFromDatabase = Float.parseFloat(reliefFromDatabase.get(i));
				expectedValue = Float.parseFloat(expectedValueJsonObj.get(natidFromDatabase.get(i).substring(0, 3)).toString());
				if (valueFromDatabase.equals(expectedValue)) {
					reliefTest="true";
				} else {
					reliefTest="false";
				}
			}
		}

		// System.out.println(obj.get("109-1234567"));

//		
		response.prettyPrint();

		System.out.println(response.statusCode());

		Assert.assertEquals(reliefTest, "true");
		Assert.assertEquals(dollarTest, "true");
		Assert.assertEquals(response.statusCode(), 200);
//	response.prettyPrint();

	}
}
