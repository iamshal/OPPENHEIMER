package API;

import static io.restassured.RestAssured.given;

import java.io.File;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InsertMultiple extends BaseTest {
	public static Response  InsertMultipleUsermethod(){
		Response response = given().contentType(ContentType.JSON).body(new File("./usersmultiple.json")).log().all().post("/calculator/insertMultiple");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		return response;
	}
	}

