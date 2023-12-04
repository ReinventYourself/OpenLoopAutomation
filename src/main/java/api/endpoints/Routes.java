package api.endpoints;

//Getuser-    https://petstore.swagger.io/v2/user/[username]
//Create user-https://petstore.swagger.io/v2/user
//Delete -https://petstore.swagger.io/v2/user/[username]
//put =https://petstore.swagger.io/v2/user/[username]


public class Routes {
	 	
public static String base_url ="https://petstore.swagger.io/v2";

//Usermodel

public static String post_url =base_url+"/user";
public static String get_url =base_url+"/user/{username}";
public static String update_url =base_url+"/user/{username}";
public static String delete_url =base_url+"/user/{username}";

}
