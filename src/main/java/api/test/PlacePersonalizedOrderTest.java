package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.Routes;
import api.utilities.Base;
import api.utilities.Utils;
import io.restassured.response.Response;
import com.jayway.jsonpath.*;
public class PlacePersonalizedOrderTest extends Base {

	Logger log;
	String Payload;
	Response res;
	@BeforeClass
	public void setUpData() {
		log = LogManager.getLogger(this.getClass());
		Payload = Utils.getPayloadFromJsonFile("PlaceNonPersonalizedOrder.json");
		Utils.getToken();
		Utils.requestBuilder();
	}

	@Test(description = "PlacePersonalized order")
	public void placeNonPersonalizedOrder() {
		res = Utils.performPost(Routes.PlaceNonPersonalizedOrder_post, Payload);
		res.then().statusCode(200);
	}

	
	
	@Test(description = "PlacePersonalized order1")
	public void placeNonPersonalizedOrder1()
	{
		String updatedPayload =  Payload;
		
	    updatedPayload=Utils.modifyJson(updatedPayload, "$.bulkShipmentAddress.streetLines[0]","8880 main st1");
		updatedPayload=	Utils.modifyJson(updatedPayload, "$.bulkShipmentAddress.streetLines[1]","");
	
		res = Utils.performPost(Routes.PlaceNonPersonalizedOrder_post, updatedPayload);
		res.then().statusCode(200);
	}
	
	
	
@Test(description = "print")
public void readpayload()
{
	String updatedPayload = Payload;
	updatedPayload=Utils.modifyJson(updatedPayload, "$.bulkShipmentAddress.streetLines[0]","8880 main st1");
	updatedPayload=	Utils.modifyJson(updatedPayload, "$.bulkShipmentAddress.streetLines[1]","99990 main st1");

 
System.out.println(updatedPayload);
System.out.println(Payload);


}

}
