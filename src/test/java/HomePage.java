import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class HomePage {
    private WebDriver webDriver;

    private WebElement welcomeMessage;

    HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements() {

        welcomeMessage = webDriver.findElement(By.xpath("//a[@data-control-name='identity_welcome_message']"));
    }

    boolean isPageLoaded(){
        return webDriver.getTitle().contains("LinkedIn") && welcomeMessage.isDisplayed();
    }
}
