import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {

        System.out.println("Hello World");

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com");

        WebElement searchField = webDriver.findElement(By.id("lst-ib"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> resultsList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println(resultsList.size());

        String selenium;
       for (WebElement text: resultsList)
       {
           selenium = text.getText();
           System.out.println(selenium);
           if (selenium.contains("Selenium"))
           {
               System.out.println("Search term found");
           }
           else
           {
               System.out.println("Search term not found");
           }
       }
    }
}
