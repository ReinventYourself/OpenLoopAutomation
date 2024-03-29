package api.utilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import api.endpoints.Routes;

public class Base {

	
	 @BeforeSuite(groups ={"Smoke"}) 
	    public void setup()
	    {
	        ConfigManager.loadConfig();
	    }
	 
	 
		@AfterSuite(groups ={"Smoke"})
		public void SendEmail()
		{
			if(ConfigManager.prop.getProperty("EmailSend").equals("True"))
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
