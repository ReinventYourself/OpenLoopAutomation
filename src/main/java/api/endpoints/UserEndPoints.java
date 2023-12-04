package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
	Response response=given()
		.contentType("application/json")
		.accept("application/json")
		.body(payload)
		.when()
		.post(Routes.post_url);
	//System.out.print("Test Payload"+" " +payload.toString());
	return response;
	}
	
	
	
	public static Response getUser(String userName)
	{
	Response response=given()
             .pathParam("username", userName)		
		
		.when()
		.get(Routes.get_url);
	return response;
		
}
	
	public static Response updateUser(User payload, String userName)
	{
	Response response=given()
		.contentType("application/json")
		.accept("application/json")
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(Routes.update_url);
	return response;
			
	}
	
	public static Response DeleteUser(String userName)
	{
	Response response=given()
             .pathParam("username", userName)		
		
		.when()
		.delete(Routes.delete_url);
	return response;
		
}
	
	
}

	