package Page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class EmailLoginPage {
    private WebDriver webDriver;

    @FindBy (xpath = "//input[@id='identifierId']")
    private  WebElement emailField;

    @FindBy (xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy (xpath = "//a[@class='WaidBe']")
    private WebElement emailLink;

    public EmailLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

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
