package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.Log;


public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		Log.info("opening chrome");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("navigating to url");
		driver.get("https://admin-demo.nopcommerce.com/");
	}
	
	@AfterMethod
	public void teardown() {
		Log.info("closing chrome");
		if(driver!=null) 
			driver.quit();
		}
	}

