package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import utils.RetryFailedTests;

public class CreateLead extends BaseClass {
	
	@BeforeTest
	public void setValue() {
		testName="CreateLead";
		testDescription="CreateLead with mandatory info";
		testCategory="Functional";
		testAuthor="Hari";
		excelFileName = "CreateLead";
	}
	
	@Test(dataProvider="provideData" /*, retryAnalyzer=RetryFailedTests.class*/)
	public void runCreateLead(String lang, String username, String password, String company,
								 String firstName, String lastName ) throws InterruptedException, IOException {
		
		File file = null;
		if(lang.equalsIgnoreCase("EN")) {
			file = new File("src/main/resources/en.properties");
			
		}else if(lang.equalsIgnoreCase("FR")) {
			file = new File("src/main/resources/fr.properties");
		}
		else if(lang.equalsIgnoreCase("DU")) {
			file = new File("src/main/resources/du.properties");
		}
		
		FileInputStream fs = new FileInputStream(file);
		prop.load(fs);
	
		//LoginPage lp= new LoginPage(driver);
		
		new LoginPage(driver)
		.enterUserName(username)
		.enterPassword(password)
		.clickLoginButton()
		.clickCrmsfaLink()
		.clickLeads()
		.clickCreateLead()
		.enterCompanyName(company)
		.enterFirstName(firstName)
		.enterLastName(lastName)
		.clickCreateLeadButton()
		.verifyFirstName();

	}

}
