import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
    private WebDriver webDriver;

    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        homePage = loginPage.login("testvikbielov@gmail.com", "112233qweqwedbrnjh");
    }

    @AfterMethod //(alwaysRun = true)
    public void afterMethod() {
        webDriver.quit();
    }

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
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");

        String searchTerm = "hr";
        SearchResultsPage searchResultsPage = homePage.searchField(searchTerm);

        Assert.assertTrue(searchResultsPage.isPageLoaded(), "Search results page is not loaded.");
    }
}
