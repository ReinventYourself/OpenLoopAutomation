package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Utils;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	String name;
    Logger log;
    
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.number().hashCode());
		name = faker.name().username();
		userPayload.setUsername(name);
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	    log = LogManager.getLogger(this.getClass());
	   Utils.requestBuilder();
	  	}
	
	
	@Test()
public void Createuserfirst()
{
   Response res =  Utils.performpost(Routes.post_url, userPayload, new HashMap<>());
   res.then().statusCode(200);
   res.then().log().all();

}

	@Test(priority = 1)
	public void createUser() {
		
		log.debug("------Creating user-------");
		Response res = UserEndPoints.createUser(userPayload);
		//res.then().log().all();
		res.then().statusCode(200).body("type", equalTo("unknown"));
		res.then().body("code", equalTo(200));
		Assert.assertEquals(res.getStatusCode(), 200);
	//	res.jsonPath().get("code");
		//res.then().time(lessThan(5000L));
	    log.debug(res.getBody().asString());
        log.debug("----User Created----");
       
	}

	@Test(priority = 2)
	public void createUserWithoutFirstName() {
		userPayload.setFirstName(null);
		log.debug("----Creating user WithoutName---");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		log.debug("---Creating user WithoutName Test case excuted---");
		
	}

	@Test(priority = 3)
	public void getuser() {
		Response res = UserEndPoints.getUser(name);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void updateuser() {
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		Response res = UserEndPoints.updateUser(userPayload, name);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 5)
	public void deleteUser() {
		Response res = UserEndPoints.DeleteUser(name);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test
	public void config()
	{
		
		//C:\RestAssuredAutomation\logs
   // Utils.enableLogging();

		System.out.println(System.getProperty("user.dir"));
		
	//RestAssuredConfig currentconfig=	RestAssured.config();
		
    //boolean log=currentconfig.getLogConfig().isLoggingOfRequestAndResponseIfValidationFailsEnabled();
	//System.out.println(log);
		
		
	}
	
	
	
}
