import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {
    private WebDriver webDriver;

    private WebElement loginForm;
    private WebElement userEmailError;
    private WebElement userPassError;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        loginForm = webDriver.findElement(By.xpath("//form[@class='login__form']"));
        userEmailError = webDriver.findElement(By.id("error-for-username"));
        userPassError = webDriver.findElement(By.id("error-for-password"));
    }

    public boolean isPageLoaded(){
        return loginForm.isDisplayed() && (userEmailError.isDisplayed() || userPassError.isDisplayed());
    }
}
