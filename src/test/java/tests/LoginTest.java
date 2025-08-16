package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtil;
import utils.ExtentReportManager;
import utils.Log;



public class LoginTest extends BaseTest{
	
	//data driven
	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException{
		String filePath = System.getProperty("user.dir")+"/Testdata/Testdata.xlsx";
		ExcelUtil.loadExcel(filePath, "sheet1");
		int rowCount = ExcelUtil.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		for(int i=1;i<rowCount;i++) {
			data[i-1][0]= ExcelUtil.getCellData(i, 0); //username
			data[i-1][1]= ExcelUtil.getCellData(i, 1);//password
		}
		ExcelUtil.closeExcel();
		return data;
	}
	
	
	@DataProvider(name="LoginData2")
	public Object[][] getData(){
		
		return new Object[][] {
			{"user4","pass"},
			{"user5","pass"},
			{"user6","pass"},
			{"user7","pass"},
			{"user8","pass"}
	};
	}
	
	
	
//	@Test(dataProvider= "LoginData2")
//	@Test
//	@Parameters({"username","password"})
//	public void testLogin(String username,String password) {
	
	
	@Test
	public void testLogin() {
//	test = ExtentReportManager.createTest("LoginTest - " + username);
	test = ExtentReportManager.createTest("LoginTest ");
	test.info("navigating to url");
	LoginPage loginPage = new LoginPage(driver);
	
	
	Log.info("entering username and password");
	loginPage.enterUsername("admin@yourstore.com");
	loginPage.enterPassword("admin");
//	loginPage.enterUsername(username);
//	loginPage.enterPassword(password);
	test.info("clicking on the login button");
	
	loginPage.login();
	
	System.out.println("title of page"+ driver.getTitle());
	Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
	test.pass("login successful");
	Log.info("Assert is successful");
}
	
	@Test
	public void loginTestWithInvalid() {
		
		test = ExtentReportManager.createTest("LoginTest");
		
		test.info("navigating to url");
		LoginPage loginPage = new LoginPage(driver);
		
		
		Log.info("entering invalid username and password");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		test.info("clicking on the login button");
		
		loginPage.login();
		
		System.out.println("title of page"+ driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Just a moment...123");
		test.pass("login successful");
		Log.info("Assert is successful");
	}
}
