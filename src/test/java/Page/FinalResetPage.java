package Page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinalResetPage {

    private WebDriver webDriver;

    @FindBy (xpath = "//input[@id='newPassword']")
    private WebElement newPassword;

    @FindBy (xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPassword;

    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPassButton;

    public FinalResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public HomePage enterNewPassword() {
        newPassword.sendKeys("newpa$$word");
        confirmPassword.sendKeys("newpa$$word", Keys.ENTER);
        resetPassButton.click();
        return new HomePage(webDriver);
    }
}
