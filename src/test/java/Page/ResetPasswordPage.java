package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage extends BasePage {

    @FindBy (xpath = "//input[@id='username']")
    private WebElement emailField;

    @FindBy (xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    @FindBy (xpath = "//header[@class='content__header']")
    private WebElement successMessage;

    ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void enterEmail(String email){
        emailField.sendKeys(email);
        resetPasswordSubmitButton.click();
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("request-password-reset")
                && webDriver.getTitle().contains("Изменить пароль")
                && emailField.isDisplayed();
    }

    public boolean isEmailSent(){
        return successMessage.isDisplayed() && webDriver.getCurrentUrl().contains("request-password-reset-submit");
    }
}
