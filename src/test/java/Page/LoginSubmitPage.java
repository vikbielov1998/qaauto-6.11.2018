package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**PageObject class for login submit page
 *
 */
public class LoginSubmitPage extends BasePage{

    /**Error text
     *
     */
    @FindBy (xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    @FindBy (id = "error-for-username")
    private WebElement userEmailError;

    @FindBy (id = "error-for-password")
    private WebElement userPassError;

    @FindBy (xpath = "//button[@class='btn__primary--large from__button--floating']")
    private WebElement signInButton;

    @FindBy (xpath = "//a[@class='btn__tertiary--medium action__btn']")
    private WebElement forgotPasswordLink;

    /**Constructor for LoginSubmitPage
     * @param webDriver webDriver instance from LoginPage
     */
    LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to get error message for email
     * @return return text of error message
     */
    public String getUserEmailErrorMessage() {
        return userEmailError.getText();
    }

    /**Method to get error message for password
     * @return return text of error message
     */
    public String getUserPassError(){
        return userPassError.getText();
    }

    /**Method to click "forgot password" link
     * @return return new instance of ResetPasswordPage
     */
    public ResetPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ResetPasswordPage(webDriver);
    }

    /**Method to check if page is loaded
     * @return return true/false (true expected)
     */
    public boolean isPageLoaded(){

        return loginForm.isDisplayed()
                && signInButton.isDisplayed()
                && webDriver.getCurrentUrl().contains("uas/login-submit");
    }
}
