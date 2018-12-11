package Test;

import Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**Parent class for all tests
 *
 */
public class BaseTest {
    protected WebDriver webDriver;

    protected LoginPage loginPage;

    /**Method executes before each test
     *
     */
    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    /**Method executes after each test
     *
     */
    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
}
