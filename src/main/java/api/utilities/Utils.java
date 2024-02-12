package api.utilities;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;

import api.endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.FailureConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	public static PrintStream log ;
    public static RequestLoggingFilter requestLoggingFilter;
    public static ResponseLoggingFilter responseLoggingFilter;
	
	public static void requestBuilder()
		{ 
		
		try {
			log = new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"//logs//testlog.text"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			RequestSpecBuilder builder = new RequestSpecBuilder();
			builder.setContentType("application/json");
			builder.setAccept("application/json");
			builder.addFilter(new RequestLoggingFilter(log));
			builder.addFilter(new ResponseLoggingFilter(log));	
			requestSpec = builder.build();
		}
	
	

	public static void responseBuilder()
		{
			ResponseSpecBuilder builder = new ResponseSpecBuilder();
			builder.expectContentType("application/json");
			builder.expectResponseTime(Matchers.lessThan(5000L));
			responseSpec = builder.build();
		}
	
	public static void enableLogging()
	{
		 RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		
	}
	
	
	public static Response performpost(String url, Object payload, Map<String, String>Headers) {
		
		Response response = given().filters(new RequestLoggingFilter(log), new ResponseLoggingFilter(log)).
				.headers(Headers)
				.body(payload)
				.when()
				.post(url);
		        return response;	
		
		
	}
	
  
	
	
}
