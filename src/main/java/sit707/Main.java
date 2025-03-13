package sit707;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/Users/kaushirajapakshe/Downloads/chromedriver-mac-x64/chromedriver");

		WebDriver driver = new ChromeDriver();
		System.out.println(driver);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}
}
