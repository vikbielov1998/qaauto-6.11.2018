package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

/**PageObject class for Request pessword reset page
 *
 */
public class RequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailField;

    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**Constructor for RequestPasswordResetPage
     * @param webDriver webDriver instance from Login page
     */
    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to check if page is loaded
     * @return return true/false (true expected)
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("request-password-reset")
                && webDriver.getTitle().contains("Изменить пароль")
                && findAccountButton.isDisplayed();
    }

    /**Method to enter email to emailField, click "find account" button and connect to Gmail Service
     * @param userEmail user email to send a request
     * @return return new instance of RequestPasswordResetSubmitPage
     */
    public RequestPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService = new GMailService();
        gMailService.connect();

        emailField.sendKeys(userEmail);
        findAccountButton.click();
        return new RequestPasswordResetSubmitPage(webDriver);
    }
}
