package api.utilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import api.endpoints.Routes;

public class Base {

	
	 @BeforeSuite
	    public void setup()
	    {
	        ConfigManager.loadConfig();
	    }
	 
	 
		@AfterSuite
		public void SendEmail()
		{
			if(ConfigManager.prop.getProperty("EmailSend").equals("true"))
			{
		      ConfigManager.SendMailSSLWithAttachment();
					} 
			else
			{
				System.out.println("If you want to send the Test result Email then turn on the setting from the config");
				
			}
			
			System.out.println("Execution Finish");
			  
	    }
		
}
