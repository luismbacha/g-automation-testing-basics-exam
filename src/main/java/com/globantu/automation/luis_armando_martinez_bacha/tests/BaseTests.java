package com.globantu.automation.luis_armando_martinez_bacha.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.globantu.automation.luis_armando_martinez_bacha.Browser;
import com.globantu.automation.luis_armando_martinez_bacha.DriverFactory;

public abstract class BaseTests {
	
	private WebDriver driver;
	private Browser browser;
	private String driverLocation;
	
	@BeforeSuite(alwaysRun=true)
	@Parameters({"browser", "driverLocation"})
	public void beforeSuite(String browser, String driverLocation) {
		this.browser = Browser.valueOf(browser);
		this.driverLocation = driverLocation;
	}
	
	@BeforeMethod
	@Parameters({"homeUrl"})
	public void beforeMethod(String homeUrl) {
		driver = DriverFactory.getDriver(browser, driverLocation);
		driver.manage().window().maximize();
		driver.get(homeUrl);
	}
	
	@AfterMethod
	public void afterMethod() {
		if(driver != null)
			for(String handler: driver.getWindowHandles())
				driver.switchTo().window(handler).close();
	}
	
	protected WebDriver getDriver() {
		return driver;
	}
	
}
