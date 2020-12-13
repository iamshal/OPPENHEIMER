package API;

import static io.restassured.RestAssured.given;



import base.BaseTest;

import io.restassured.response.Response;

public class GetTaxRelief extends BaseTest {
	public static Response  getTax(){
		
		Response resposne = given().get("/calculator/taxRelief");
		return resposne;
		
		
	}
	} 


