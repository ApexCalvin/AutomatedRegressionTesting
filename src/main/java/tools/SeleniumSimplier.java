package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumSimplier {

	private static final WebDriver driver = new ChromeDriver();

	public static void launch(String website) {
		//WebDriverManager.chromedriver().setup();
		driver.get(website);
		driver.manage().window().maximize();
	}

	public static void click_byId(String objectName) {
		driver.findElement(By.id(objectName)).click();
	}

	public static void click_byName(String objectName) {
		driver.findElement(By.name(objectName)).click();
	}
	
	public static void click_byClassName(String objectName) {
		driver.findElement(By.className(objectName)).click();
	}
	
	// selenium UI practice, submit button
	public static void click_byXpath(String objectName) {
		driver.findElement(By.className(objectName)).click();
	}

	public static void type_byId(String objectName, String content) {
		driver.findElement(By.id(objectName)).sendKeys(content);
	}

	public static void type_byName(String objectName, String content) {
		driver.findElement(By.name(objectName)).sendKeys(content);
	}

	public static void select_fromDropDown_byId(String objectName, String option) {
		WebElement dropdown = driver.findElement(By.id(objectName));
		new Select(dropdown).selectByVisibleText(option);
	}
	
	public static void select_fromDropDown_byName(String objectName, String option) {
		WebElement dropdown = driver.findElement(By.name(objectName));
		new Select(dropdown).selectByVisibleText(option);
	}
	
	public static void select_fromDataList_byId(String objectName, String option) throws InterruptedException {
		WebElement dropdown = driver.findElement(By.id(objectName));
		dropdown.click();
		dropdown.sendKeys(option);
		Thread.sleep(1000);
		dropdown.sendKeys(Keys.TAB);
	}
	
	// data list drop down elements are input elms instead of select elms
	public static void select_fromDataList_byName(String objectName, String option) throws InterruptedException {
		WebElement dropdown = driver.findElement(By.name(objectName));
		dropdown.click();
		dropdown.sendKeys(option);
		Thread.sleep(1000);
		dropdown.sendKeys(Keys.TAB);
	}

	public static void uploadfile_byCSS(String objectName, String filePath) {
		driver.findElement(By.cssSelector(objectName)).sendKeys(filePath);
		// click submit button if needed
	}
	
	public static void select_current_date_byId(String objectName) {
		WebElement elm = driver.findElement(By.id(objectName));
		//new Select(elm).selectByVisibleText(date);
	}

	public static void select_current_date_byName(String objectName) {
		WebElement elm = driver.findElement(By.name(objectName));
		//new Select(elm).selectByVisibleText(date);
	}
	
	public static void select_specific_date_byId(String objectName, String date) {
		WebElement elm = driver.findElement(By.id(objectName));
		new Select(elm).selectByVisibleText(date);
	}

	public static void select_specific_date_byName(String objectName, String date) {
		WebElement elm = driver.findElement(By.name(objectName));
		new Select(elm).selectByVisibleText(date);
	}

	public static void slideRange_byName_usingJavascript(String objectName, int value) {
		WebElement elm = driver.findElement(By.name(objectName));
		javascript_setValueAttribute(elm, String.valueOf(value));	
	}
	
	// fails because elm is not visible even when clicked, must be set using JS
	public static void slideRange_byName_usingXpathClicking(String objectName, int value) {
		
		WebElement sliderWithNewValue = driver.findElement(By.xpath("//input[@name='" + objectName + "' and @value='" + value + "']")); // Update the XPath to match your element
		sliderWithNewValue.click();

		WebElement slider = driver.findElement(By.name(objectName));
		//System.out.println("[Notice] Updated slider value: " + slider.getAttribute("value"));
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
	
	// sliding range elements, changes "value" attribute
	public static void javascript_setValueAttribute(WebElement slider, String value) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].setAttribute('value', arguments[1]);", slider, value);
	    //System.out.println("[Notice] Updated slider value: " + slider.getAttribute("value"));
	}
}
