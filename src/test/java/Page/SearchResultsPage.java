package Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFilterBar;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> resultList;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("search/results") && webDriver.getTitle().contains("Search | LinkedIn") && searchFilterBar.isDisplayed();
    }

    public int getSize(){
        return resultList.size();
    }

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