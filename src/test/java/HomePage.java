import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class HomePage extends BasePage{

    @FindBy (xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;

    HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    boolean isPageLoaded(){

        return webDriver.getTitle().contains("LinkedIn") && welcomeMessage.isDisplayed();
    }
}
