package seleniumPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	private WebDriver webDriver;
	
	@FindBy(id="username")
	WebElement userNameInputFild;
	
	@FindBy(id="dob")
	WebElement dobInputFild;
	
	@FindBy(id="passwd")
	WebElement passwordInputFIld;
	
	@FindBy(xpath ="//input[@type='submit']")
	WebElement loginButton;
	
	@FindBy(id="error")
	WebElement errorElement;
	
    public void enterFildUsernameInput(String userName) {
    	userNameInputFild.sendKeys(userName);
    }

    public void enterFildPasswordInput(String password) {
    	passwordInputFIld.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void enterFildDOBInput(String dob) {
    	dobInputFild.sendKeys(dob);
    }
    
    public void submitLoginForm(String userName, String password, String dob) {
        enterFildUsernameInput(userName);
        enterFildPasswordInput(password);
        enterFildDOBInput(dob);
        clickLoginButton();
    }
	
    public boolean isLoginPageDisplayed() {
        return userNameInputFild.isDisplayed() &&
        	   passwordInputFIld.isDisplayed() &&
               dobInputFild.isDisplayed() &&
               loginButton.isDisplayed();
    }
	
	public Login(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	public boolean checkError() {
		return errorElement.getText().equals("Wrong answer, try again.");
	}
	
}
