package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class MyLeadPage extends BaseClass{
	
	public MyLeadPage(RemoteWebDriver driver) {
		this.driver=driver;
	}
	
	public CreateLeadPage clickCreateLead() {
		driver.findElement(By.linkText(prop.getProperty("MyLeads_CreateLeadLink"))).click();
		return new CreateLeadPage(driver);

	}

}
