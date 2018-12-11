package Page;

import org.openqa.selenium.Keys;
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

    /**Method to set and confirm new password
     * @return return Home page
     */
    public HomePage enterNewPassword() {
        newPasswordField.sendKeys("pa$$word");
        confirmPasswordField.sendKeys("pa$$word", Keys.ENTER);
        resetPassButton.click();
        return new HomePage(webDriver);
    }
    public boolean isPageLoaded() {
        return newPasswordField.isDisplayed() && webDriver.getTitle().contains("Reset your password");
    }
}
