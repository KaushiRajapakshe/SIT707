package sit707_week2;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Ahsan Habib
 */
public class MainTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
    private final String url = "https://www.bunnings.com.au/login";

	@Before
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"/Users/kaushirajapakshe/Downloads/chromedriver-mac-x64/chromedriver");
		System.out.println("Fire up chrome browser.");
		
		driver = new ChromeDriver();
		System.out.println("Driver info: " + driver);

		wait = new WebDriverWait(driver, 5000);
		driver.get(url);
	}
	
	@Test
	public void testStudentIdentity() {
		String studentId = "s223681886";
		Assert.assertNotNull("Student ID is not null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Kaushalya Rajapaksha";
		Assert.assertNotNull("Student name is not null", studentName);
	}
	
	private void login(String username, String password) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
        driver.findElement(By.id("login-submit")).click();
    }
	
	@Test
	public void testValidUsernameAndValidPassword() {
		login("kaushi.rajapakshe1@gmail.com", "Kaushi1211*");
		SeleniumOperations.saveScreenshot(driver, "bunnings1.png");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}
	
	@Test
	public void testWrongFormatUsernameAndValidPassword() {
		login("kaushi.rajapakshe1", "Kaushi1211*");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		System.out.print(driver.getCurrentUrl());
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}

	@Test
	public void testInValidUsernameAndValidPassword() {
		login("kaushi.rajapakshe@gmail.com", "Kaushi1211*");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		System.out.print(driver.getCurrentUrl());
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}

	@Test
	public void testValidUsernameAndInValidPassword() {
		login("kaushi.rajapakshe1@gmail.com", "Kaushi1211#");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		System.out.print(driver.getCurrentUrl());
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}
	
	@Test
	public void testInvalidUsernameAndInvalidPassword() {
		login("kaushi.rajapakshe@gmail.com", "Kaushi1211#");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		System.out.print(driver.getCurrentUrl());
		SeleniumOperations.saveScreenshot(driver, "bunnings2.png");
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}
	@Test
	public void testEmptyUsernameAndValidPassword() {
		login("", "Kaushi1211#");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		System.out.print(driver.getCurrentUrl());
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}

	@Test
	public void testValidUsernameAndEmptyPassword() {
		login("kaushi.rajapakshe1@gmail.com", "");
		wait.until(ExpectedConditions.urlContains("https://www.bunnings.com.au/"));
		System.out.print(driver.getCurrentUrl());
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}

	@Test
	public void testEmptyUsernameAndEmptyPassword() {
		login("", "");
		System.out.print(driver.getCurrentUrl());
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.bunnings.com.au/"));
	}	
	
	@After
	public void cleanUp() {
		driver.quit();
	}
}
