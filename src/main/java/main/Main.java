package main;

import pom.HomePage;
import utility.Utility;

import java.io.IOException;

import org.testng.Assert;

import capabilities.Capabilities;

public class Main extends Capabilities {

	public void prepare() throws IOException {
		// TODO start application with appium
		driver = baseCapabilities("AppName");
		// TODO Declare pom objects
		homePage = new HomePage(driver);
		// TODO take screenshot
		Utility.captureScreenshot(driver, "homepage");
	}

	public void simpleMethod() throws InterruptedException {
		// TODO Simple Example
		homePage.simpleExamble1.click();
		Thread.sleep(2000);
		// TODO take screenshot at any phase
		Utility.captureScreenshot(driver, "screenshotName");
		// TODO Simple Example to validate result
		Assert.assertEquals(false, false);
	}

	public void TearDown() {
		// TODO close driver and shutdown
		driver.quit();
	}
}
