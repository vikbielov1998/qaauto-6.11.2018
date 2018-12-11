package Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**PageObject for search results page
 *
 */
public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFilterBar;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> resultList;

    /**Constructor for SearchResultsPage
     * @param webDriver webDriver instance from HomePage
     */
    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

        waitUntilElementIsVisible(searchFilterBar, 5);
    }

    /**Method to check if page is loaded
     * @return return true/false (true expected)
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("search/results") && webDriver.getTitle().contains("Search | LinkedIn") && searchFilterBar.isDisplayed();
    }

    /**Method to find size of results list
     * @return return size of result list
     */
    public int getSize(){
        return resultList.size();
    }

    /**Method to add elements to a list
     * @return results list
     */
    public List<String> getSearchResults() {
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResult : resultList){
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", searchResult);
            String searchResultText = searchResult.getText();
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;
    }
}