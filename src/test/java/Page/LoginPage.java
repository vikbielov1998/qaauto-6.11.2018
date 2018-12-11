package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/** PageObject class for LoginPage.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    @FindBy (xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /** Constructor of LoginPage class.
     * @param webDriver - webDriver instance from Test.
     */
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to return class depends on what page will load
     * @param userEmail - user login email
     * @param userPassword - user login password (valid or invalid)
     * @param <T> - generic type of returned PageObject
     * @return returns HomePage / LoginSubmitPage / LoginPage class
     */
    public <T> T login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        if (webDriver.getCurrentUrl().contains("/feed")){
            return (T) new HomePage(webDriver);
    }   if (webDriver.getCurrentUrl().contains("/login-submit")){
            return (T) new LoginSubmitPage(webDriver);
        } else {
            return (T) new LoginPage(webDriver);
        }
    }

    /** Method to check if page is loaded.
     * @return true/false (true expected).
     */
    public boolean isPageLoaded() {

        return signInButton.isDisplayed() && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться") && webDriver.getCurrentUrl().equals("https://www.linkedin.com/");
    }

    /** Method to click on 'forgot password' link.
     * @return new object of RequestPasswordResetPage class.
     */
    public RequestPasswordResetPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(webDriver);
    }
}
