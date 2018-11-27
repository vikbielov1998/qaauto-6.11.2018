import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    /*@Test
    public void wrongPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@gmail.com", "pa$$word");

        String passError = webDriver.findElement(By.id("error-for-password")).getText();

        Assert.assertEquals(passError, "Это неверный пароль. Повторите попытку или измените пароль.");
    }

    @Test
    public void emailWithoutDot() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@gmailcom", "112233qweqwedbrnjh");

        String emailError = webDriver.findElement(By.id("error-for-username")).getText();

        Assert.assertEquals(emailError, "Этот адрес эл. почты не зарегистрирован в LinkedIn.\nВозможно, вы имели в виду @gmail.com?");
    }

    @Test
    public void emailWithoutCom() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@gmail.", "112233qweqwedbrnjh");

        String emailError = webDriver.findElement(By.id("error-for-username")).getText();

        Assert.assertEquals(emailError, "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.");
    }

    @Test
    public void invalidEmail() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov", "112233qweqwedbrnjh");

        String emailError = webDriver.findElement(By.id("error-for-username")).getText();

        Assert.assertEquals(emailError, "Укажите действительный адрес эл. почты.");
    }*/
    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"testvikbielov@gmail.com", "112233qweqwedbrnjh"},
                {"testvikbielov@GMAIL.COM", "112233qweqwedbrnjh"},
                {" testvikbielov@gmail.com ", "112233qweqwedbrnjh"}
        };
    }

    @Test (dataProvider = "validDataProvider")
    public void positiveLoginTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }

    @Test
    public void emptyField() {
        LoginPage loginPage = new LoginPage(webDriver);
        LoginPage loginPage1 = loginPage.login("", "");

        Assert.assertTrue(loginPage1.isPageLoaded(), "Login page is not loaded.");
    }

    @Test
    public void negativeLeadsToLoginSubmitPage(){
        LoginPage loginPage = new LoginPage(webDriver);
        LoginSubmitPage loginSubmitPage = loginPage.login("testvikbielov@@gmail.com", "112233qweqwedbrnjh");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded");
    }
}