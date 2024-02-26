package api.utilities;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	static TestNG testng ;
	
	public static void main(String[] args) {
	
		testng  = new TestNG();
		String path = System.getProperty("user.dir")+"/testng.xml";
		List<String> xmllist = new ArrayList<String>();
		xmllist.add(path);
		testng.setTestSuites(xmllist);
		testng.run();
		
		

	}

}
