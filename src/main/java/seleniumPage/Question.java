package seleniumPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Question {
	   private WebDriver webDriver;

	    @FindBy(id = "number1")
	    private WebElement number1Input;

	    @FindBy(id = "result")
	    private WebElement resultInput;
	    
	    @FindBy(xpath="//h2")
	    private WebElement header;
	    
	    @FindBy(id = "number2")
	    private WebElement number2Input;

	    @FindBy(xpath = "//input[@type='submit']")
	    private WebElement submitButton;
	    
	    @FindBy(id="error")
	    private WebElement errorElement;

	    public Question(WebDriver webDriver) {
	        this.webDriver = webDriver;
	        PageFactory.initElements(webDriver, this);
	    }

	    public void enterFirstNumberInput(String number) {
	        number1Input.sendKeys(number);
	    }

	    public void enterResultInput(String answer) {
	        resultInput.sendKeys(answer);
	    }
	    
	    public void enterSecondNumberInput(String number) {
	        number2Input.sendKeys(number);
	    }

	    public void clickSubmitButton() {
	        submitButton.click();
	    }

	    public void submitForm(String number1, String number2, String answer) {
	        enterFirstNumberInput(number1);
	        enterSecondNumberInput(number2);
	        enterResultInput(answer);
	        clickSubmitButton();
	    }
	    
	    public boolean checkHeader(String hText) {
	    	return header.getText().equals(hText);
	    }

	    public boolean isQPageDisplayed(String pageName) {
	        return number1Input.isDisplayed() &&
	               number2Input.isDisplayed() &&
	               submitButton.isDisplayed() && checkHeader(pageName) ;
	    }
	    
	    public boolean checkError() {
	    	return errorElement.getText().equals("Wrong answer, try again.");
	    }
}
