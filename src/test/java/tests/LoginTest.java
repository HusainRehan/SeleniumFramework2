package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.Log;



public class LoginTest extends BaseTest{
	
	@Test
	public void testLogin() {
	LoginPage loginPage = new LoginPage(driver);
	Log.info("entering username and password");
	loginPage.enterUsername("admin@yourstore.com");
	loginPage.enterPassword("admin");
	loginPage.loginButton();
	System.out.println("title of page"+ driver.getTitle());
	Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
	Log.info("Assert is successful");
}
}
