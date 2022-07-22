package test_cases;

import org.testng.annotations.Test;

import pages.PageBase;

public class E2E_TestCase001_CheckHomePage extends TestBase {

	@Test(priority = 1, groups = "smoke", description = "Add Test Description here")
	public static void checkSummationOperation() throws InterruptedException {
		mainPage.SimpleAndroidElement.click();
		PageBase.assertToObjectExistWithGetText(mainPage.SimpleAndroidElement, "expected string");// assert to specific
																									// text
		PageBase.captureScreenshot(driver, "verifySummation");// take screenshot after summation operation
	}
}
