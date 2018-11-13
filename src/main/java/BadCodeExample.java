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
        String searchTerm = "selenium";

        WebElement searchField = webDriver.findElement(By.id("lst-ib"));
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> resultsList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println(resultsList.size());

        //for each WebElement 'result' in list of WebElements 'resultsList' print Text
        String resultText;
        for (WebElement result : resultsList) {
            resultText = result.getText();
            System.out.println(resultText);
            if (resultText.toLowerCase().contains(searchTerm)) {
                System.out.println("Search term found");
            } else {
                System.out.println("Search term not found");
            }
        }
        webDriver.quit();
    }
}
