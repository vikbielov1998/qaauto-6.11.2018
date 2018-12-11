package Page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**Class for email login page
 *
 */
public class EmailLoginPage {
    private WebDriver webDriver;

    @FindBy (xpath = "//input[@id='identifierId']")
    private  WebElement emailField;

    @FindBy (xpath = "//input[@name='password']")
    private WebElement passwordField;

    /**
     * Link (image) to go to email box from google page
     */
    @FindBy (xpath = "//a[@class='WaidBe']")
    private WebElement emailLink;

    /**Constructor for EmailLoginPage class
     * @param webDriver webDriver instance from ResetPasswordPage
     */
    public EmailLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to login to google account and go to email
     * @param userMail user login email fo Google account (valid)
     * @param userPass user login password to Google account (valid)
     * @return return class EmailBoxPage
     */
    public EmailBoxPage login(String userMail, String userPass) {
        emailField.sendKeys(userMail, Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordField.sendKeys(userPass, Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailLink.click();
        return new EmailBoxPage(webDriver);
    }
}
