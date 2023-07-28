package APITesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import groovyjarjarantlr.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateBooking {

	@Test
	public void UpdateBooking()
	{
		//2631
		
		 
		 
		 
		 Response resp = RestAssured.get("https://restful-booker.herokuapp.com/booking/43");
		 System.out.println(resp.asPrettyString());
		 
		 
		 
		 resp = RestAssured.given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.cookie("abc123")
					.body("{\r\n"
							+ "    \"firstname\" : \"Mahender\",\r\n"
							+ "    \"lastname\" : \"Brown\",\r\n"
							+ "    \"totalprice\" : 111,\r\n"
							+ "    \"depositpaid\" : true,\r\n"
							+ "    \"bookingdates\" : {\r\n"
							+ "        \"checkin\" : \"2018-01-01\",\r\n"
							+ "        \"checkout\" : \"2019-01-01\"\r\n"
							+ "    },\r\n"
							+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
							+ "}")
					.put("https://restful-booker.herokuapp.com/booking/43");
					
					System.out.println("Final Response Code - " + resp.getStatusCode());
					System.out.println("Final Response - "+ resp.asPrettyString());
		 
	}
}
