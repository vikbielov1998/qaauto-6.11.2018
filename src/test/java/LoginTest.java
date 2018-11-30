import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver webDriver;

    private LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"testvikbielov@gmail.com", "112233qweqwedbrnjh"},
                {"testvikbielov@GMAIL.COM", "112233qweqwedbrnjh"},
                {" testvikbielov@gmail.com ", "112233qweqwedbrnjh"}
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void positiveLoginTest(String userEmail, String userPass) {
        HomePage homePage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }


    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                {"testvikbielov@gmail.com", ""},
                {"", "112233qweqwedbrnjh"},
                {"", ""}
        };
    }

    @Test(dataProvider = "emptyDataProvider")
    public void emptyField(String userEmail, String userPass) {
        LoginPage loginPage1 = loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginPage1.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"testvikbielov@@gmail.com", "112233qweqwedbrnjh", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},                       //wrong email
                {"testvikbielov@gmail.com", "pa$$word", "", "Это неверный пароль. Повторите попытку или измените пароль."},                                             //wrong password
                {"testvikbielov@gmailcom", "112233qweqwedbrnjh", "Этот адрес эл. почты не зарегистрирован в LinkedIn.\nВозможно, вы имели в виду @gmail.com?", ""},     //email without dot
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String emailErrorMessage, String passErrorMessage) {
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded());
        Assert.assertEquals(loginSubmitPage.getUserEmailErrorMessage(), emailErrorMessage, "Wrong email error message");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), passErrorMessage, "Wrong password error message");
    }
}