package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import API.InsertMultiple;
import API.InsertUser;
import io.restassured.response.Response;

public class InsertMultipleTestcase extends BaseTest  {
	@Test
	public void Insert() {
	Response response = InsertMultiple.InsertMultipleUsermethod();
	
	response.prettyPrint();

	System.out.println(response.statusCode());

	Assert.assertEquals(response.statusCode(), 202);
	response.prettyPrint();

}


}



