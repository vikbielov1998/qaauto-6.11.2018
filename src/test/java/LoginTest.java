import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void negativeLoginTest() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com");

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));

        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("");
        signInButton.click();

        //Verify that page Title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться");
    }


    @Test
    public void positiveLoginTest() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com");

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));

        emailField.sendKeys("testvikbielov@gmail.com");
        passwordField.sendKeys("112233qweqwedbrnjh");
        signInButton.click();

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn");
    }
}