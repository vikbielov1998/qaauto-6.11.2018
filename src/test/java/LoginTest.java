import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    @Test
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
    }

    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@gmail.com", "112233qweqwedbrnjh");

        HomePage homePage = new HomePage(webDriver);

        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"), "Home page title is wrong");
        Assert.assertTrue(homePage.welcomeMessage.isDisplayed(), "Welcome message is not displayed");
    }

    @Test
    public void emptyFied() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @Test
    public void negativeLeadsToLoginSubmitPage(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@@gmail.com", "112233qweqwedbrnjh");

        WebElement loginForm = webDriver.findElement(By.xpath("//form[@class='login__form']"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login Submit page is not loaded");

        WebElement userEmailError = webDriver.findElement(By.id("error-for-username"));
        Assert.assertEquals(userEmailError.getText(), "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "userEmail validation message is wrong");

        WebElement userPassError = webDriver.findElement(By.id("error-for-password"));
        Assert.assertEquals(userPassError.getText(), "", "userPass validation message is wrong");
    }
}