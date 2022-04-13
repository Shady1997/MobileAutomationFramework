package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	public HomePage(AppiumDriver driver) {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	//Simple Examble
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@index='1']")
	public WebElement simpleExamble1;

	// Another Simple Examble
	@AndroidFindBy(xpath = "//android.widget.Button[@text='1']")
	public WebElement simpleExamble2;
}
