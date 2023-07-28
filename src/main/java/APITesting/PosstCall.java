package APITesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PosstCall 
{

	@Test
	public void CreateBooking()
	{
		Response resp = RestAssured.given()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"firstname\" : \"Mahender\",\r\n"
				+ "    \"lastname\" : \"Dhoni\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
		.post("https://restful-booker.herokuapp.com/booking");
		
		System.out.println("Response Code - " + resp.getStatusCode());
		System.out.println(resp.asPrettyString());
		
		
		
	}
}
