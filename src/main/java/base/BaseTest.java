package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;


public class BaseTest {
	
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	
	@BeforeSuite
	public void setupReport() {
	extent = ExtentReportManager.getReportInstance();}
	
	@AfterSuite
	public void teardownReport() {
		extent.flush();
		String reportPath = ExtentReportManager.reportPath;
		EmailUtils.sendTestReports(reportPath);
	}
	
	@BeforeMethod
	public void setup() {
		Log.info("opening chrome");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("navigating to url");
		driver.get("https://admin-demo.nopcommerce.com/");
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			String ScreenshotPath = ExtentReportManager.captureScreenshot(driver, "Login Failure");
			test.fail("test failed .. check Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotPath).build());
		}
		Log.info("closing chrome");
		if(driver!=null) 
			driver.quit();
		}
	}

