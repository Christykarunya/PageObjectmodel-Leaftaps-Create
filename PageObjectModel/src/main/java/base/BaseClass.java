package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ReadExcel;


public class BaseClass {
	
	public RemoteWebDriver driver;
	public String excelFileName;
	public static Properties prop = new Properties();
	
	public static ExtentReports extent;
	public String testName, testDescription, testCategory, testAuthor;
	public static ExtentTest test;
	
	@BeforeSuite
	public void startReport() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);	
	}
	
	@BeforeClass
	public void testcaseDetails() {
		extent.createTest(testName, testDescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
	}
	
	public int takeSnap() throws IOException {
		int ranNum= (int) (Math.random()*999999+999999);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File target=new File("./snaps/img"+ranNum+".png");
		FileUtils.copyFile(src, target);
		return ranNum;

	}
	
	public void reportStep(String msg, String status) throws IOException {
		if(status.equalsIgnoreCase("pass")) {
			test.pass(msg, MediaEntityBuilder
							.createScreenCaptureFromPath(".././snaps/img"+takeSnap()+".png")
							.build());
		} else if(status.equalsIgnoreCase("fail")) {
			test.fail(msg, MediaEntityBuilder
							.createScreenCaptureFromPath(".././snaps/img"+takeSnap()+".png")
							.build());
		}

	}
	
	@AfterSuite
	public void stopReport() {
		extent.flush();

	}
	
	@DataProvider(indices=0)
	public String[][] provideData() throws IOException {
		return ReadExcel.readData(excelFileName);
	}
	
	@Parameters({"browser"})
	@BeforeMethod
	public void preCondition(String browser) {
		switch(browser.toLowerCase()) {
		case "chrome" :
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox" :
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default :	
			System.out.println("The browser is important to be passed");
		}
		
		System.out.println(driver);
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	@AfterMethod
	public void postCondition() {
		driver.close();

	}
	

}
