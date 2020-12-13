package API;

import static io.restassured.RestAssured.given;

import java.io.File;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InsertUser extends BaseTest{
	public static Response  InsertUsermethod(){
	Response response = given().contentType(ContentType.JSON).body(new File("./users.json")).log().all().post("/calculator/insert");
	response.prettyPrint();
	System.out.println(response.getStatusCode());
	return response;
}
}


