package Page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**Class for page to set and confirm new password
 *
 */
public class FinalResetPage {

    private WebDriver webDriver;

    /**
     * Field to enter new password
     */
    @FindBy (xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    /**
     * Field to confirm new password
     */
    @FindBy (xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    /**
     * Button to set new password
     */
    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPassButton;

    /**Constructor for FinalResetPage class
     * @param webDriver webDriver (new window) from EmailBoxPage
     */
    public FinalResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to set and confirm new password
     * @return return Home page
     */
    public HomePage enterNewPassword() {
        newPasswordField.sendKeys("newpa$$word");
        confirmPasswordField.sendKeys("newpa$$word", Keys.ENTER);
        resetPassButton.click();
        return new HomePage(webDriver);
    }
}
