package api.endpoints;

import org.testng.annotations.BeforeSuite;

import api.utilities.ConfigManager;

public class Routes  {
	
	// Order Module
	public static String Token_post = ConfigManager.base_url + "/api/tokens/token";

	public static String PlaceNonPersonalizedOrder_post = ConfigManager.base_url
			+ "/client/orderservice/PlaceNonPersonalizedOrder";

	public static String PlacePersonalizedOrder_post = ConfigManager.base_url
			+ "/client/orderservice/PlacePersonalizedOrder";

}
