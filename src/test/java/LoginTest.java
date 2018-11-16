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
    public void negativeLoginTest() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("a@b.c", "");
        //Verify that page Title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is wrong");
    }


    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("testvikbielov@gmail.com", "112233qweqwedbrnjh");
        String name = webDriver.findElement(By.xpath("//span[@class='t-16 t-black t-bold']")).getText();
        Assert.assertEquals(name, "Viktor Bielov");
    }
}