package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public static ExtentTest test;
	public static String methodName;
	public static String repName;
	public static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<>();
   public static  int passCount=0;
   public static int failCount =0;
	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";

		sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName);
		sparkreporter.config().setDocumentTitle("OpenLoopAutomaton");
		sparkreporter.config().setReportName("OpenLoop API Report " + ConfigManager.Env);
		sparkreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "OpenLoop API Report");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));

	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getDescription());
		extenttest.set(test);
		methodName = result.getMethod().getDescription();
		
	}

	public void onTestSuccess(ITestResult result) {
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
         passCount++;
	}

	public void onTestFailure(ITestResult result) {
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		String stacktrace = Arrays.toString(result.getThrowable().getStackTrace());
		stacktrace  =stacktrace.replaceAll(",", "<br>");
		String formatedTrace ="<details>\n"+
		"    <summary>click here to see exception logs</summary>\n  "+
		"	"+stacktrace+"\n" +
			"</details>\n";
		logFailuredetails(formatedTrace);
	    failCount++;
	}

	public void onTestSkipped(ITestResult result) {
		// test = extent.createTest(result.getMethod().getDescription());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

	}

	public static void loginfodetails(String log) {
		extenttest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));

	}

	public static void logFailuredetails(String log) {
		extenttest.get().fail(log);

	}

}
