package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginServiceTest {
	
	private WebDriver driver;
    private final String url = "file:///Users/kaushirajapakshe/Documents/Deakin/MSC/Year 2/T1/SIT707/7.1 P/7.1P-resources/pages/login.html";
	
	private void sleep(long sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    @Before
    public void setUp() {
        // Set your Chromedriver path
		System.setProperty(
				"webdriver.chrome.driver", 
				"/Users/kaushirajapakshe/Downloads/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Driver info: " + driver);

		driver.navigate().to(url);
    }
	
    private String login(String username, String password, String dob) {
    	sleep(5);
    	driver.findElement(By.id("username")).clear();
    	driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("dob")).clear();
        driver.findElement(By.id("dob")).sendKeys(dob);
        driver.findElement(By.cssSelector("[type=submit]")).submit();
        sleep(5);
        return driver.getTitle();
    }
    
	@Test
	public void testLoginSuccess() {
	
		String title = login("kaushi", "kaushi_pass", "11-12-1995");
		/*
		 * On successful login, the title of page changes to 'success',
		 * otherwise, 'fail'.
		 */
		System.out.println("Title: " + title);
		
		Assert.assertEquals(title, "success");
		
		driver.close();
	}
	
	
	
	@Test
	public void testLoginFailAll() {
	
		String title = login("kaushi1", "kaushi1_pass2", "10/12/1995");
		
		/*
		 * On successful login, the title of page changes to 'success',
		 * otherwise, 'fail'.
		 */
		System.out.println("Title: " + title);
		
		Assert.assertEquals(title, "fail");
		
		driver.close();
	}
	
	@Test
	public void testLoginFailUserName() {
	
		String title = login("ahsan1", "ahsan_pass", "10-11-1990");
		
		/*
		 * On successful login, the title of page changes to 'success',
		 * otherwise, 'fail'.
		 */
		System.out.println("Title: " + title);
		
		Assert.assertEquals(title, "fail");
		
		driver.close();
	}
	
	@Test
	public void testLoginFailPassword() {
	
		String title = login("ahsan", "ahsan_pass2", "10-11-1990");

		/*
		 * On successful login, the title of page changes to 'success',
		 * otherwise, 'fail'.
		 */
		System.out.println("Title: " + title);
		
		Assert.assertEquals(title, "fail");
		
		driver.close();
	}
	
	
	@Test
	public void testLoginFailDOB() {
	
		String title = login("ahsan", "ahsan_pass", "10/11/1990");
		
		/*
		 * On successful login, the title of page changes to 'success',
		 * otherwise, 'fail'.
		 */
		System.out.println("Title: " + title);
		
		Assert.assertEquals(title, "fail");
		
		driver.close();
	}
	
	@Test
	public void testLoginNullInput() {
	
		String title = login("", "", "");
		/*
		 * On successful login, the title of page changes to 'success',
		 * otherwise, 'fail'.
		 */
		System.out.println("Title: " + title);
		
		Assert.assertEquals(title, "fail");
		
		driver.close();
	}
	
	
	@After
	public void cleanUp() {
		driver.quit();
	}
}
