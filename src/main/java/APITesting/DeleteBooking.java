package APITesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteBooking {

	@Test
	public void DeleteBooking()
	{
		Response token  =  RestAssured.given()
				 .contentType(ContentType.JSON)
				 .body("\"username\" : \"admin\",\r\n"
				 		+ "    \"password\" : \"password123\"")
				 .post("https://restful-booker.herokuapp.com/auth");
				 
				 System.out.println("Token - " +token.asPrettyString());
	}
}
