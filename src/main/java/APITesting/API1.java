package APITesting;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API1 
{

	@Test
	public void getStatus()
	{
		
		 Response resp= RestAssured.get("https://restful-booker.herokuapp.com/booking");
		 
		 int statusCode = resp.getStatusCode();
		 System.out.println("Status - " +resp.getStatusCode());
		 Assert.assertEquals(statusCode, 200);
		 System.out.println("----------");
		 
		 //837
		 resp= RestAssured.get("https://restful-booker.herokuapp.com/booking/43");
		 System.out.println(resp.asPrettyString());
		 JsonPath json  = resp.jsonPath();
		 System.out.println("Chekin date is - " + json.get("firstname"));
		 Assert.assertNotNull(json.get("firstname"));
		 
		 
	}	
}
