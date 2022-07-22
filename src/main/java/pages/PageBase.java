package pages;

import java.io.File;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageBase {

	// Scroll to view Element
	public static void scrollToViewElement(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	// Scroll to view element in Android
	public static void scrollToViewAndroidElement(AndroidDriver<AndroidElement> driver, String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));").click();
	}

	// Assertion to object exists after specific action with getText() method
	public static void assertToObjectExistWithGetText(WebElement element, String expected) {
		Assert.assertEquals(element.getText().toString(), expected);
	}

	// Assertion to object exists after specific action with getAttribute() method
	public static void assertToObjectExistWithGetAttribute(WebElement element, String attributeName, String expected) {
		Assert.assertEquals(element.getAttribute(attributeName).toString(), expected);
	}

	// Scroll with specific amount over web page
	public static void scrollWithSpecificSize(WebDriver driver, int sizeX, int sizeY) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String jsCommand = String.format("window.scrollBy('%s','%s')", sizeX, sizeY);
		js.executeScript(jsCommand, "");
	}

	// Execute javascript script to
	public static void executeavascriptScript(String jsCommand, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jsCommand);
	}

	// Take Screenshot
	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		try {
			FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\Screenshots\\" + screenshotName + ".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read Data From Excel Sheet
	public static String getExcelData(int RowNum, int ColNum, String SheetName) {
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		String projectPath = System.getProperty("user.dir");
		String cellData = null;
		try {
			workBook = new XSSFWorkbook(projectPath + "\\src\\test\\resources\\data_driven\\data.xlsx");
			sheet = workBook.getSheet(SheetName);
			cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return cellData;
	}
}
