package api.utilities;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import api.endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	public static PrintStream log;
	public static RequestLoggingFilter requestLoggingFilter;
	public static ResponseLoggingFilter responseLoggingFilter;
	public static File F;
	public static FileReader FR;
	public static JSONTokener JT;
	public static JSONObject JO;
	private static String token;

	public static void requestBuilder() {

	    setLogFile();
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType("application/json");
		builder.setAccept("application/json");
		builder.addHeader("Authrization", "Bearer" + " " + token);
		builder.addFilter(new RequestLoggingFilter(log));
		builder.addFilter(new ResponseLoggingFilter(log));
		requestSpec = builder.build();
	}

	public static void responseBuilder() {
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectContentType("application/json");
		builder.expectResponseTime(Matchers.lessThan(5000L));
		responseSpec = builder.build();
	}


	public static Response performpostwithoutAuth(String url, Object payload) {
		setLogFile();
		Response response = given().filters(new RequestLoggingFilter(log), new ResponseLoggingFilter(log))
				.contentType("Application/json").body(payload.toString()).when().post(url);
		printResponseLogInReport(response);
		return response;

	}

	private static Response postrequest(String url, Object payload) {
		Response response = given().contentType("Application/json").body(payload.toString()).when().post(url);
		return response;

	}

	public static Response performPost(String url, Object payload) {
		setLogFile();
		Response response = given().filters(new RequestLoggingFilter(log), new ResponseLoggingFilter(log))
				.contentType("Application/json").header("Authorization", "Bearer" + " " + token)
				.body(payload.toString()).when().post(url);
		return response;

	}

	public static Response performPost(String url, String payload) {
		setLogFile();
		Response response = given().filters(new RequestLoggingFilter(log), new ResponseLoggingFilter(log))
				.contentType("Application/json").header("Authorization", "Bearer" + " " + token).body(payload).when()
				.post(url);
		printResponseLogInReport(response);
		return response;

	}	
	
//=================================================================	
	public static void printResponseLogInReport(Response response)
	{
		ExtentReportManager.loginfodetails("Status code is " + response.getStatusCode());
		ExtentReportManager.loginfodetails("Response headers are " + response.headers().asList().toString());
		ExtentReportManager.loginfodetails("Respose Body " + response.getBody().asPrettyString());		
	}
	
//=============================================================================
	public static String getToken() {
		Response res = postrequest(Routes.Token_post, getPayloadFromJsonFile("Token.json"));
		res.then().statusCode(200);
		token = res.jsonPath().get("accessToken").toString();
		return token;

	}

	private static void setLogFile() {
		   try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				String currentDate = dateFormat.format(new Date());
		        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String currentDatewithseconds = dateFormat1.format(new Date());


		        File logsFolder = new File(System.getProperty("user.dir") + "//logs//" + currentDate);
		        if (!logsFolder.exists()) {
		            logsFolder.mkdirs(); 
		        }

		        String logFileName = logsFolder.getAbsolutePath() + "//testlog_" + ExtentReportManager.methodName + currentDatewithseconds +".txt";
		        log = new PrintStream(new FileOutputStream(logFileName, true));
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
	}

	public static String modifyJson(String OriginaljsonString, String jsonPath, Object newValue) {
		try {
			DocumentContext documentContext = JsonPath.parse(OriginaljsonString);
			documentContext.set(jsonPath, newValue);
			return documentContext.jsonString();
		} catch (Exception e) {
		      e.printStackTrace();
		      Assert.fail("Issue with Json Modification, Please check the Jsonpath" +jsonPath);
		}
		
		return OriginaljsonString;
	}

	public static String getPayloadFromJsonFile(String FileName) {

		try {

			//System.out.println(ConfigManager.Env);
			if (ConfigManager.Env.equals("QA")) {
				F = new File(
						System.getProperty("user.dir") + "/src/main/resources/RequestPayloadTestData/QA/" + FileName);
			}

			if (ConfigManager.Env.equals("Staging")) {
				F = new File(System.getProperty("user.dir") + "/src/main/resources/RequestPayloadTestData/Staging/"
						+ FileName);
			}
			FR = new FileReader(F);
			JT = new JSONTokener(FR);
			JO = new JSONObject(JT);
			return JO.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Error in JSON file: " + e.getMessage());
			return null;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

}
