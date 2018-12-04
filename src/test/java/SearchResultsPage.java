import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFilterBar;

    @FindBy(xpath = "//li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> resultList;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("search/results") && webDriver.getTitle().contains("Search | LinkedIn") && searchFilterBar.isDisplayed();
    }

    int getSize(){
        return resultList.size();
    }

   /*List<WebElement> getResult(){
       return resultList;
    }*/

    boolean getResult(String searchTerm)
    {
        for (WebElement result : resultList){
            if (!result.getText().toLowerCase().contains(searchTerm)) return false;
        }
        return true;
    }
}
