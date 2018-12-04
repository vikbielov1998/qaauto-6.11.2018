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

       //Assert.assertTrue(searchResultsPage.isResult(searchTerm), "Not all results contains searchTerm!");

        /*for (WebElement result : searchResultsPage.getResult()){
            Assert.assertTrue(result.getText().toLowerCase().contains(searchTerm), "Some result doesn't contain searchTerm");
        }*/

        List<String> searchResultsList = searchResultsPage.getSearchResults();

        for (String searchResult : searchResultsList){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "SearchTerm " + searchTerm + " not found in:\n" + searchResult);
        }
    }
}