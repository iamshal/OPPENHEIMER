package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import API.InsertUser;
import io.restassured.response.Response;

public class InsertUserTestcase extends BaseTest  {
	@Test
	public void Insert() {
	Response response = InsertUser.InsertUsermethod();
	
	response.prettyPrint();

	System.out.println(response.statusCode());

	Assert.assertEquals(response.statusCode(), 202);
	response.prettyPrint();

}


}
