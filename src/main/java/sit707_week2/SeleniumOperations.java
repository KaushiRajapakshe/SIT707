package sit707_week2;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.commons.io.FileUtils;

/**
 * This class demonstrates Selenium locator APIs to identify HTML elements.
 * 
 * Details in Selenium documentation
 * https://www.selenium.dev/documentation/webdriver/elements/locators/
 * 
 * @author Ahsan Habib
 */
public class SeleniumOperations {

	public static void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void officeworks_registration_page(String url) {
		// Step 1: Locate chrome driver folder in the local drive.
		System.setProperty("webdriver.chrome.driver",
				"/Users/kaushirajapakshe/Downloads/chromedriver-mac-x64/chromedriver");

		// Step 2: Use above chrome driver to open up a chromium browser.
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();

		System.out.println("Driver info: " + driver);

		sleep(2);

		// Load a webpage in chromium browser.
		driver.get(url);

		/*
		 * How to identify a HTML input field - Step 1: Inspect the webpage, Step 2:
		 * Locate the input field, Step 3: Find out how to identify it, by id/name/...
		 */

		// Find first input field which is firstname
		WebElement element = driver.findElement(By.id("firstname"));
		System.out.println("Found element: " + element);
		// Send first name
		element.sendKeys("Isuri");

		/*
		 * Find following input fields and populate with values
		 */
		// Write code

		// Find second input field which is lastname
		WebElement elementln = driver.findElement(By.id("lastname"));
		System.out.println("Found element: " + elementln);
		// Send last name
		elementln.sendKeys("Kaushalya");

		// Find third input field which is phoneNumber
		WebElement elementpn = driver.findElement(By.id("phoneNumber"));
		System.out.println("Found element: " + elementpn);
		// Send phone number
		elementpn.sendKeys("0430075432");

		// Find fourth input field which is email
		WebElement elemente = driver.findElement(By.id("email"));
		System.out.println("Found element: " + elemente);
		// Send email
		elemente.sendKeys("kaushi.rajapakshe1@gmail.com");
		// elemente.sendKeys("s223681886@deakin.edu.au");

		// Find fifth input field which is password
		WebElement elementp = driver.findElement(By.id("password"));
		System.out.println("Found element: " + elementp);
		// Send password
		elementp.sendKeys("Kaushi123");

		// Find sixth input field which is confirmPassword
		WebElement elementcp = driver.findElement(By.id("confirmPassword"));
		System.out.println("Found element: " + elementcp);
		// Send confirm password
		elementcp.sendKeys("Kaushi123");

		// Verify if an error message appears
		WebElement elementerror = driver.findElement(By.id("password-helper-text"));

		// Check password valid or invalid
		if (elementerror.isDisplayed()) {
			System.out.println("Error message displayed for password: " + elementerror.getText());
		} else {
			System.out.println("No error message for password detected.");
		}
		/*
		 * Take screenshot using selenium API.
		 */
		// Write code
		saveScreenshot(driver, "officeworks_error.png");

		// Clear password 
		elementp.clear();

		// Send password
		elementp.sendKeys("Kaushi123*");

		// Clear confirm password 
		elementcp.clear();

		// Send confirm password
		elementcp.sendKeys("Kaushi123*");

		// Verify if an error message appears
		boolean isErrorDisplayed = driver.findElements(By.id("password-helper-text")).size() > 0;

		if (isErrorDisplayed) {
            System.out.println("Error message displayed for password.");
        } else {
            System.out.println("No error message for password detected.");
        }

		/*
		 * Identify button 'Create account' and click to submit using Selenium API.
		 */
		// Write code
		WebElement elementcab = driver.findElement(By.cssSelector("button[data-testid='account-action-btn']"));
		elementcab.click();
		
		sleep(5);

		/*
		 * Take screenshot using selenium API.
		 */
		// Write code
		saveScreenshot(driver, "officeworks.png");

		// Sleep a while
		sleep(2);

		// close chrome driver
		driver.close();
	}

	public static void theoodie_registration_page(String url) {
		// Step 1: Locate chrome driver folder in the local drive.
		System.setProperty("webdriver.chrome.driver",
				"/Users/kaushirajapakshe/Downloads/chromedriver-mac-x64/chromedriver");

		// Step 2: Use above chrome driver to open up a chromium browser.
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();

		System.out.println("Driver info: " + driver);

		sleep(2);

		// Load a webpage in chromium browser.
		driver.get(url);

		/*
		 * How to identify a HTML input field - Step 1: Inspect the webpage, Step 2:
		 * Locate the input field, Step 3: Find out how to identify it, by id/name/...
		 */

		// Find first input field which is firstname
		WebElement element = driver.findElement(By.id("RegisterForm-FirstName"));
		System.out.println("Found element: " + element);
		// Send first name
		element.sendKeys("Isuri");

		/*
		 * Find following input fields and populate with values
		 */
		// Write code

		// Find second input field which is lastname
		WebElement elementln = driver.findElement(By.id("RegisterForm-LastName"));
		System.out.println("Found element: " + elementln);
		// Send last name
		elementln.sendKeys("Kaushalya");

		// Find fourth input field which is email
		WebElement elemente = driver.findElement(By.id("RegisterForm-email"));
		System.out.println("Found element: " + elemente);
		// Send email
		// elemente.sendKeys("s223681886@deakin.edu.au");
		elemente.sendKeys("kaushi.rajapakshe1@gmail.com");

		// Find fifth input field which is password
		WebElement elementp = driver.findElement(By.id("RegisterForm-password"));
		System.out.println("Found element: " + elementp);
		// Send password
		elementp.sendKeys("Kaushi123*");

		/*
		 * Identify button 'Create' and click to submit using Selenium API.
		 */
		// Write code
		WebElement elementcab = driver.findElement(By.xpath("//button[contains(text(),'Create')]"));
		elementcab.click();

		/*
		 * Take screenshot using selenium API.
		 */
		// Write code
		saveScreenshot(driver, "theoodie.png");

		// Sleep a while
		sleep(2);

		// close chrome driver
		driver.close();

	}

	public static void saveScreenshot(WebDriver driver, String screenshot) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// FileUtils.copyFile(scrFile, new File("/Users/kaushirajapakshe/Downloads/" + screenshot));
			FileUtils.copyFile(scrFile, new File("/" + screenshot));

			System.out.println("Screenshot taken successfully.");

		} catch (IOException e) {
			System.out.println("Failed to save screenshot.");
			e.printStackTrace();
		}
	}
}
