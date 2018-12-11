package Page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**Page object for LinkedIn home page
 *
 */
public class HomePage extends BasePage{

    @FindBy (xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;

    @FindBy (xpath = "//div[@class='nav-search-typeahead']//input")
    private WebElement searchField;

    /**Constructor for HomePage class
     * @param webDriver webDriver instance from LoginPage/FinalResetPage
     */
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to check if page is loaded
     * @return true/false (true expected)
     */
    public boolean isPageLoaded(){

        return webDriver.getTitle().contains("LinkedIn") && welcomeMessage.isDisplayed();
    }

    /**Method to search some word on search field
     * @param searchTerm word ^^
     * @return return SearchResultPage class
     */
    public SearchResultsPage searchField(String searchTerm) {
        searchField.sendKeys(searchTerm, Keys.ENTER);

        return new SearchResultsPage(webDriver);
    }
}
