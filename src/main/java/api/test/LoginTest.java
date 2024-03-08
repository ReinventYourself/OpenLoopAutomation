package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.Routes;
import api.utilities.Base;
import api.utilities.Utils;
import io.restassured.response.Response;

public class LoginTest extends Base {

	Logger log;
	String Payload;

	@BeforeClass(groups ={"Smoke"})
	public void setUpData() {
		log = LogManager.getLogger(this.getClass());
		Payload = Utils.getPayloadFromJsonFile("Token.json");

	}

	@Test(description = "Get Token",groups ={"Smoke"})
	public void getToken() {
		Response res = Utils.performpostwithoutAuth(Routes.Token_post, Payload);
		res.then().statusCode(200);
		String Token = res.jsonPath().get("accessToken").toString();

	}

	@Test(description = "Veriy Token Without Passing username")
	public void getTokenWithoutUserName() {

		String updatedPayload = Utils.modifyJson(Payload, "$.userName", "");
		Response res = Utils.performpostwithoutAuth(Routes.Token_post, updatedPayload);
		res.then().statusCode(422);
		String ErrorMessage = res.body().asPrettyString();
		Assert.assertEquals(ErrorMessage, "[\"A valid value for User Name is required for authentication.\"]");

	}


	@Test(description = "Veriy Token Without Passing Password")
	public void getTokenWithoutPassword() {

		String updatedPayload = Utils.modifyJson(Payload, "$.password", "");
		Response res = Utils.performpostwithoutAuth(Routes.Token_post, updatedPayload);
		res.then().statusCode(422);
		String ErrorMessage = res.body().asPrettyString();
		Assert.assertEquals(ErrorMessage, "[\"Password is required.\"]");

	}
	
	
	@Test(description = "Veriy Token Without Passing ClientID")
	public void getTokenWithoutclientId() {

		String updatedPayload = Utils.modifyJson(Payload, "$.clientId", "");
		Response res = Utils.performpostwithoutAuth(Routes.Token_post, updatedPayload);
		res.then().statusCode(422);
		String ErrorMessage = res.body().asPrettyString();
		Assert.assertEquals(ErrorMessage, "[\"ClientId is required.\"]");

	}
	
	
	
	
}
