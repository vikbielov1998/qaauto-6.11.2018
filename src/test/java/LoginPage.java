import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class LoginPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    LoginSubmitPage loginToLoginSubmit(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new LoginSubmitPage(webDriver);
    }

    HomePage loginToHome(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new HomePage(webDriver);
    }

    public LoginPage login(String userEmail, String userPassword){
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new LoginPage(webDriver);
    }

    boolean isPageLoaded() {

        return signInButton.isDisplayed() && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться") && webDriver.getCurrentUrl().equals("https://www.linkedin.com/");
    }
}
