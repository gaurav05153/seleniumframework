package extentReport;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.Logging;


public class ExtentReport 
{
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	public ExtentReport()
	{		
		extentReports = new ExtentReports();	
		spark = new ExtentSparkReporter("Reports\\Regression.html");
		spark.config().setDocumentTitle("Relevel Automation Regression Suite");
		extentReports.attachReporter(spark);
		extentReports.setSystemInfo("Project Name", "Relevel");
		extentReports.setSystemInfo("Version", "Relevel 1.0513");
	}

	public void pass(String text) throws IOException
	{		
		test.log(Status.PASS,text);
	}	
	
	public void fail(String text) throws IOException
	{
		test.log(Status.FAIL,text);
	}
	
	public void info(String text)
	{
		test.log(Status.INFO,text);
	}

	public void flush()
	{
		extentReports.flush();
	}
	
	public ExtentTest createTest(String testName)
	{
		test = extentReports.createTest(testName);	
		Logging.startTestCase(testName);
		return test;
	}
	
	public void addScreenshot(WebDriver driver) throws IOException
	{
		test.addScreenCaptureFromPath(utils.Utility.captureScreenshot(driver));
	}

}
