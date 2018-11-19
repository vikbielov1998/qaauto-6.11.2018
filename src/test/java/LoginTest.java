import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;

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
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@gmail.com", "112233qweqwedbrnjh");

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn");
    }

    @Test
    public void everythingElse() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("", "");

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться");
    }
}