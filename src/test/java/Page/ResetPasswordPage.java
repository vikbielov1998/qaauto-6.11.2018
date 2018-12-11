package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**PageObject class for Request password reset page
 *
 */
public class ResetPasswordPage extends BasePage {

    @FindBy (xpath = "//input[@id='username']")
    private WebElement emailField;

    /**
     * "find account" button
     */
    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    /**
     * Message says that the mail was send
     */
    @FindBy (xpath = "//header[@class='content__header']")
    private WebElement successMessage;

    /**Constructor for ResetPasswordPage
     * @param webDriver webDriver instance from LoginSubmitPage
     */
    ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to enter email and click "find account" button
     * @param email user email to send a request
     */
    public void enterEmail(String email){
        emailField.sendKeys(email);
        resetPasswordSubmitButton.click();
    }

    /**Method to check if page is loaded
     * @return return true/false (true expected)
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("request-password-reset")
                && webDriver.getTitle().contains("Изменить пароль")
                && emailField.isDisplayed();
    }

    /**Method to check if email was send
     * @return return true/false (true expected)
     */
    public boolean isEmailSent(){
        return successMessage.isDisplayed() && webDriver.getCurrentUrl().contains("request-password-reset-submit");
    }
}
