package web.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestRoutingServlet {

    private WebDriver webDriver;

    public static String BASE_URL = "http://localhost:8080";
    public static String SUCCESS = "/success";
    public static String LOGIN = "/login";
    public static String Q1 = "/q1";
    public static String Q2 = "/q2";
    public static String Q3 = "/q3";

    public static String userName = "Kaushi";
    public static String password = "Kaushi_pass";
    public static String dob = "11/12/1995";

    @Before
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/kaushirajapakshe/Downloads/chromedriver-mac-x64/chromedriver");
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testWelcomePageLoadsSuccessfully(){
        webDriver.get(BASE_URL+"/");
        String pageSource = webDriver.getPageSource();
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Welcome"));
        assertTrue("Welcome page loaded",pageSource.contains("Server is running..."));
    }
    @Test
    public void testLoginPageLoadsSuccessfully(){
        webDriver.get(BASE_URL+LOGIN);
        String pageSource = webDriver.getPageSource();
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("HTML Forms"));
        assertTrue("Login page loaded",pageSource.contains("If you click the \"Login\" button, the form-data will be sent to login-servlet.") || pageSource.contains("Login"));
    }
    @Test
    public void testNavigationOFLoginPage(){
        webDriver.get(BASE_URL+"/");
        webDriver.findElement(By.cssSelector("a[href='/login']")).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(LOGIN));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("HTML Forms"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(LOGIN));
    }
    @Test
    public void testLoginWithValidCredentialsRedirectsToQ1(){
        webDriver.get(BASE_URL+LOGIN);
        webDriver.findElement(By.id("username")).sendKeys(userName);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("dob")).sendKeys(dob);
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q1));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q1"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q1));
    }
    @Test
    public void testLoginWithInvalidCredentialsShowsError(){
        webDriver.get(BASE_URL+LOGIN);
        webDriver.findElement(By.id("username")).sendKeys(userName);
        webDriver.findElement(By.id("passwd")).sendKeys("wrong_pass");
        webDriver.findElement(By.id("dob")).sendKeys(dob);
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(LOGIN));
        Assertions.assertTrue(webDriver.getPageSource().contains("Incorrect credentials."));
    }
    @Test
    public void testQ1PageLoadsSuccessfully(){
        webDriver.get(BASE_URL+Q1);
        assertTrue("Q1 page loaded",webDriver.findElement(By.tagName("h2")).getText().contains("Q1"));
    }
    @Test
    public void testQ1WithCorrectAnswerRedirectsToQ2(){
        webDriver.get(BASE_URL+Q1);
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("50");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q2));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q2"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q2));
    }
    @Test
    public void testQ1WithWrongAnswerStaysOnQ1WithError(){
        webDriver.get(BASE_URL+Q1);
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("40");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q1));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q1"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q1));
        Assertions.assertTrue(webDriver.getPageSource().contains("Wrong answer, try again."));
    }
    @Test
    public void testQ1WithEmptyInputsShowsValidationError(){
        webDriver.get(BASE_URL+Q1);
        webDriver.findElement(By.id("number1")).sendKeys("");
        webDriver.findElement(By.id("number2")).sendKeys("");
        webDriver.findElement(By.id("result")).sendKeys("");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q1));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q1"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q1));
        Assertions.assertTrue(webDriver.getPageSource().contains("Wrong answer, try again."));
    }
    @Test
    public void testQ2PageLoadsSuccessfully(){
        webDriver.get(BASE_URL+Q2);
        assertTrue("Q2 page loaded",webDriver.findElement(By.tagName("h2")).getText().contains("Q2"));
    }
    @Test
    public void testQ2WithCorrectAnswerRedirectsToQ3(){
        webDriver.get(BASE_URL+Q2);
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("26");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q3));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q3"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q3));
    }
    @Test
    public void testQ2WithWrongAnswerStaysOnQ2WithError(){
        webDriver.get(BASE_URL+Q2);
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("40");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q2));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q2"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q2));
        Assertions.assertTrue(webDriver.getPageSource().contains("Wrong answer, try again."));
    }
    @Test
    public void testQ2WithEmptyInputsShowsValidationError(){
        webDriver.get(BASE_URL+Q2);
        webDriver.findElement(By.id("number1")).sendKeys("");
        webDriver.findElement(By.id("number2")).sendKeys("");
        webDriver.findElement(By.id("result")).sendKeys("");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q2));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q2"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q2));
        Assertions.assertTrue(webDriver.getPageSource().contains("Wrong answer, try again."));
    }
    @Test
    public void testQ3PageLoadsSuccessfully(){
        webDriver.get(BASE_URL+Q3);
        assertTrue("Q3 page loaded",webDriver.findElement(By.tagName("h2")).getText().contains("Q3"));
    }
    @Test
    public void testQ3WithCorrectAnswerRedirectsToSuccess(){
        webDriver.get(BASE_URL+Q3);
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("50");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(SUCCESS));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Congratulation Student"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(SUCCESS));
    }
    @Test
    public void testQ3WithWrongAnswerStaysOnQ3WithError(){
        webDriver.get(BASE_URL+Q3);
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("40");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q3));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q3"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q3));
        Assertions.assertTrue(webDriver.getPageSource().contains("Wrong answer, try again."));
    }
    @Test
    public void testQ3WithEmptyInputsShowsValidationError(){
        webDriver.get(BASE_URL+Q3);
        webDriver.findElement(By.id("number1")).sendKeys("");
        webDriver.findElement(By.id("number2")).sendKeys("");
        webDriver.findElement(By.id("result")).sendKeys("");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q3));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q3"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q3));
        Assertions.assertTrue(webDriver.getPageSource().contains("Wrong answer, try again."));
    }
    @Test
    public void testSuccessPageLoadsCorrectly(){
        webDriver.get(BASE_URL+SUCCESS);
        assertTrue("Success page loaded",webDriver.findElement(By.tagName("h2")).getText().contains("Congratulation Student"));
    }
    @Test
    public void testBackNavigationWorksCorrectly(){
        webDriver.get(BASE_URL+LOGIN);
        webDriver.findElement(By.id("username")).sendKeys(userName);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("dob")).sendKeys(dob);
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q1));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Q1"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(Q1));
        webDriver.navigate().back();
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("HTML Forms"));
        String pageSource = webDriver.getPageSource();
        assertTrue("Login page loaded",pageSource.contains("If you click the \"Login\" button, the form-data will be sent to login-servlet.") || pageSource.contains("Login"));

    }
    @Test
    public void testFullQuizFlowFromLoginToSuccess(){
        webDriver.get(BASE_URL+"/");
        webDriver.findElement(By.cssSelector("a[href='/login']")).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(LOGIN));
        webDriver.findElement(By.id("username")).sendKeys(userName);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("dob")).sendKeys(dob);
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q1));
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("50");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q2));
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("26");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(Q3));
        webDriver.findElement(By.id("number1")).sendKeys("38");
        webDriver.findElement(By.id("number2")).sendKeys("12");
        webDriver.findElement(By.id("result")).sendKeys("50");
        webDriver.findElement(By.cssSelector("[type=submit]")).submit();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(SUCCESS));
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().contains("Congratulation Student"));
        Assertions.assertTrue(webDriver.getCurrentUrl().contains(SUCCESS));
    }
}
