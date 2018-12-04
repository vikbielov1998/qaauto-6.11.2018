package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

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

   /* private   <T> T login(String userEmail, String userPassword, Class<T> expectedPage) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return PageFactory.initElements(webDriver, expectedPage);
    }

    Page.HomePage homePage(String userEmail, String userPassword) {
        return login(userEmail, userPassword, Page.HomePage.class);
    }

    Page.LoginSubmitPage loginToLoginSubmit(String userEmail, String userPassword) {
        return login(userEmail, userPassword, Page.LoginSubmitPage.class);
    }

    Page.LoginPage loginPage(String userEmail, String userPassword) {
        return login(userEmail, userPassword, Page.LoginPage.class);
    }*/

    public boolean isPageLoaded() {

        return signInButton.isDisplayed() && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться") && webDriver.getCurrentUrl().equals("https://www.linkedin.com/");
    }
}
