package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailField;

    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("request-password-reset")
                && webDriver.getTitle().contains("Изменить пароль")
                && findAccountButton.isDisplayed();
    }

    public RequestPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService = new GMailService();
        gMailService.connect();

        emailField.sendKeys(userEmail);
        findAccountButton.click();
        return new RequestPasswordResetSubmitPage(webDriver);
    }
}
