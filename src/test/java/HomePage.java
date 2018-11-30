import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

class HomePage extends BasePage{

    @FindBy (xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;

    @FindBy (xpath = "//div[@class='nav-search-typeahead']//input")
    private WebElement searchField;

    HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    boolean isPageLoaded(){

        return webDriver.getTitle().contains("LinkedIn") && welcomeMessage.isDisplayed();
    }

    public SearchResultsPage searchField(String searchTerm) {
        searchField.sendKeys(searchTerm, Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResultsPage(webDriver);
    }
}
