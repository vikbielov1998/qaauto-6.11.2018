package Test;

import Page.HomePage;
import Page.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {

    /**
     * Precondition:
     * - open browser
     * - navigate to linkeddin.com
     * - login with valid data
     *
     * Scenario:
     * - Verify Home page is loaded.
     * - enter "hr" to search field. Press ENTER.
     * - Verify page is loaded.
     * - Check amount of results ~= 10.
     * - Verify each item contains searchTerm
     *
     * Postcondition:
     * - Close the browser.
     */

    @Test
    public void basicSearchTest(){
        HomePage homePage = loginPage.login("testvikbielov@gmail.com", "112233qweqwedbrnjh");

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");

        String searchTerm = "hr";
        SearchResultsPage searchResultsPage = homePage.searchField(searchTerm);

        Assert.assertTrue(searchResultsPage.isPageLoaded(), "Search results page is not loaded.");

        Assert.assertEquals(searchResultsPage.getSize(), 10, "Amount of result element does not equal to 10");

        List<String> searchResultsList = searchResultsPage.getSearchResults();

        for (String searchResult : searchResultsList){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm), "SearchTerm " + searchTerm + " not found in:\n" + searchResult);
        }
    }
}