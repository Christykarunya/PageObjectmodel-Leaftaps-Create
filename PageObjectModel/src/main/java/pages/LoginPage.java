package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class LoginPage extends BaseClass {
	
	public LoginPage(RemoteWebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.ID, using="username")
	private WebElement eleUserName;
	
	@FindBy(how=How.ID, using="password")
	private WebElement elePassword;
	
	@FindBy(how=How.CLASS_NAME, using="decorativeSubmit")
	private WebElement eleLogin;
	
	
					//action+ElementName
	public LoginPage enterUserName(String uName) throws InterruptedException {
		//System.out.println(driver);
		eleUserName.sendKeys(uName);
		//Thread.sleep(5000);
		return this;
	}
	
	public LoginPage enterPassword(String pwd) {
		elePassword.sendKeys(pwd);
		return this;

	}
	
	public HomePage clickLoginButton() {
		eleLogin.click();
		//HomePage hp = new HomePage();
		return new HomePage(driver);
		

	}

}
