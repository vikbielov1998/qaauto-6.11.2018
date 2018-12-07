package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage extends BasePage{

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

    LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getUserEmailErrorMessage() {
        return userEmailError.getText();
    }

    public String getUserPassError(){
        return userPassError.getText();
    }

    public ResetPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ResetPasswordPage(webDriver);
    }

    public boolean isPageLoaded(){

        return loginForm.isDisplayed()
                && signInButton.isDisplayed()
                && webDriver.getCurrentUrl().contains("uas/login-submit");
    }
}
