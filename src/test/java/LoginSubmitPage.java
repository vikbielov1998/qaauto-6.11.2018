import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LoginSubmitPage {
    private WebDriver webDriver;

    private WebElement loginForm;
    private WebElement userEmailError;
    private WebElement userPassError;
    private WebElement signInButton;

    LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        loginForm = webDriver.findElement(By.xpath("//form[@class='login__form']"));
        userEmailError = webDriver.findElement(By.id("error-for-username"));
        userPassError = webDriver.findElement(By.id("error-for-password"));
        signInButton = webDriver.findElement(By.xpath("//button[@class='btn__primary--large from__button--floating']"));
    }

    boolean isPageLoaded(){
        return loginForm.isDisplayed() && signInButton.isDisplayed() && (userEmailError.isDisplayed() || userPassError.isDisplayed()) && webDriver.getCurrentUrl().contains("uas/login-submit");
    }
}
