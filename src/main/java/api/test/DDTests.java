package api.test;

import org.testng.annotations.Test;
import org.testng.Assert;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;
// Data Driven testing 
public class DDTests {
	
	@Test(priority =1, dataProvider="Data", dataProviderClass=Dataproviders.class)
	public void testpostuser(String UserID, String UserName,String FirstName,String LastName,String Email ,String Password, String Phone) {
		
		User userPayload = new User();
		userPayload.setId(UserID);
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
        userPayload.setPhone(Phone);

       Response res = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);

	}
	
	@Test(priority =2, dataProvider="UserNames", dataProviderClass=Dataproviders.class)
	public void deleteuser(String UserName) {
		Response res = UserEndPoints.DeleteUser(UserName);
//		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	

}
