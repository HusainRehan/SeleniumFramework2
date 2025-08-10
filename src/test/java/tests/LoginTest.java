package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	
	@Test
	public void testLogin() {
	LoginPage loginPage = new LoginPage(driver);
	loginPage.enterUsername("admin@yourstore.com");
	loginPage.enterPassword("admin");
	loginPage.loginButton();	

}
}
