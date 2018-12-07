package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy (xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPassButton;

    public SetNewPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return newPasswordField.isDisplayed() && webDriver.getTitle().contains("Reset tour password");
    }
}
