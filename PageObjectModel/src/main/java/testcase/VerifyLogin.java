package testcase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class VerifyLogin extends BaseClass {
	
	@BeforeTest
	public void setValue() {
		testName="VerifyLogin";
		testDescription="Login with positive data";
		testCategory="Smoke";
		testAuthor="Hari";
		excelFileName = "Login";
	}
	
	
	@Test(dataProvider="provideData")
	public void runVerifyLogin(String username, String password) throws InterruptedException {
		
		LoginPage lp= new LoginPage(driver);
		lp.enterUserName(username)
		.enterPassword(password)
		.clickLoginButton()
		.verifyHomePage();

	}

}
