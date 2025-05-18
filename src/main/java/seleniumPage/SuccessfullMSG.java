package seleniumPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessfullMSG {
	 private WebDriver webDriver;

	    @FindBy(xpath = "//h2")
	    private WebElement congratulationsHeader;

	    @FindBy(xpath = "//h2/following-sibling::text()[1]")
	    private WebElement completionMessage;

	    public SuccessfullMSG(WebDriver webDriver) {
	        this.webDriver = webDriver;
	        PageFactory.initElements(webDriver, this);
	    }

	    public boolean isCongratulationsPageDisplayed() {
	        return congratulationsHeader.getText().equals("Congratulation Student");
	    }

	    public String getCompletionMessage() {
	        return completionMessage.getText();
	    }
}
