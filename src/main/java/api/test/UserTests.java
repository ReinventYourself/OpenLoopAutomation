package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
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
	}

	@Test(priority = 1)
	public void createUser() {
		
		log.debug("------Creating user-------");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
       log.debug("----User Created----");
	}

	@Test(priority = 2)
	public void createUserWithoutFirstName() {
		userPayload.setFirstName(null);
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		// res.then().time(Matchers.lessThan(1000L));
		AssertJUnit.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void getuser() {
		Response res = UserEndPoints.getUser(name);
		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void updateuser() {
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		Response res = UserEndPoints.updateUser(userPayload, name);
		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 5)
	public void deleteUser() {
		Response res = UserEndPoints.DeleteUser(name);
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
	}

}
