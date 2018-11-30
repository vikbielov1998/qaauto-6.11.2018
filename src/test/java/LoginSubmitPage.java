import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class LoginSubmitPage extends BasePage{

    @FindBy (xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    @FindBy (id = "error-for-username")
    private WebElement userEmailError;

    @FindBy (id = "error-for-password")
    private WebElement userPassError;

    @FindBy (xpath = "//button[@class='btn__primary--large from__button--floating']")
    private WebElement signInButton;

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

    boolean isPageLoaded(){

        return loginForm.isDisplayed()
                && signInButton.isDisplayed()
                && webDriver.getCurrentUrl().contains("uas/login-submit");
    }
}
