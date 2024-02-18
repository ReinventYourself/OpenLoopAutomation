package api.utilities;

import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CustomValidationFailureListener implements ResponseValidationFailureListener {

	@Override
	public void onFailure(RequestSpecification requestSpecification, ResponseSpecification responseSpecification,
			Response response) {
		    System.out.println("Custom Validation Failure Listener triggered!");
	        System.out.println("Response status code: " + response.getStatusCode());
	        System.out.println("Response body: " + response.getBody().asString());
	}

}
