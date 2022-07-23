package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

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

import database.DatabaseConnection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageBase {

	// Scroll to view Element
	public static void scrollToViewElement(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	// use robot library for keyboard control
	public static void changeKeyBoard() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyRelease(KeyEvent.VK_ALT);
		r.keyRelease(KeyEvent.VK_SHIFT);
	}

	// generate random string for data driven test
	public static String generateString(int StringLength) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = StringLength;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	// generate random character
	public static char generateCharacter() {

		return generateString(1).charAt(0);
	}

	// generate random integer number
	public static int generateInteger(int upperBound) {
		Random rand = new Random(); // instance of random class
		// generate random values from 0-24
		int int_random = rand.nextInt(upperBound);
		return int_random;
	}

	// generate random float number
	public static float generateFloat(int digitLength) {
		Random rand = new Random(); // instance of random class
		float float_random = rand.nextFloat();
		DecimalFormat df = new DecimalFormat(getDecimalFormat(digitLength));// format double digits
		float p = Float.parseFloat(df.format(float_random));
		return p;
	}

	// generate random double number
	public static double generateDouble(int digitLength) {
		Random rand = new Random(); // instance of random class
		double double_random = rand.nextDouble();// generate double number
		DecimalFormat df = new DecimalFormat(getDecimalFormat(digitLength));// format double digits
		double p = Double.parseDouble(df.format(double_random));
		return p;
	}

	// get decimal format
	public static String getDecimalFormat(int digitLength) {
		String doubleFormat = "#.";
		for (int i = 0; i < digitLength; i++) {
			doubleFormat += "#";
		}
		return doubleFormat;
	}

	// connect to mysql database
	public static ResultSet getResultSet(String dbName, String port, String userName, String password, String query)
			throws SQLException {
		@SuppressWarnings("unused")
		DatabaseConnection databaseConnection = new DatabaseConnection(dbName, port, userName, password);
		// start connect
		DatabaseConnection.createDBConnection();
		// create statement
		DatabaseConnection.createStatement();
		// read data
		ResultSet resultSet = DatabaseConnection.dbResultSet(query);
		return resultSet;
	}

	// close connection to database
	public static void closeConnection() throws SQLException {
		DatabaseConnection.closedbConnection();
	}
	/*
	 * simple way to use database connection try { ResultSet rs =
	 * getResultSet("test", "3306", "root", "", "SELECT * FROM USERS WHERE ID=5");
	 * while (rs.next()) System.out.println(rs.getInt(1) + "  " + rs.getString(2) +
	 * "  " + rs.getString(3)); closeConnection(); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 */

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
