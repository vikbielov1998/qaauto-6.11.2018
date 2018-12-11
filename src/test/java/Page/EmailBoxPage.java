package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**Page object for email webpage
 *
 */
public class EmailBoxPage {

    private WebDriver webDriver;

    /**
     * List of messages
     */
    @FindBy (xpath = "//tr[contains(@class, 'zA')]")
    private List<WebElement> emailList;

    /**
     * Link to reset a password
     */
    @FindBy (xpath = "//*/div[1]/table/tbody/tr/td/center/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/p/a")
    private WebElement resetLink;

    /**Constructor of EmailBoxPage class
     * @param webDriver webDriver instance from EmailLoginPage
     */
    public EmailBoxPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**Method to find correct mail and click a "Reset password" link
     * @return return class FinalResetPage / null
     */
    public FinalResetPage findCorrectMail() {
        for(int i = 0; i < emailList.size(); i++){
            if(emailList.get(i).getText().contains("LinkedIn")){
                emailList.get(i).click();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (resetLink.isDisplayed()) {
                    resetLink.click();
                    ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
                    webDriver.switchTo().window(tabs.get(1));
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return new FinalResetPage(webDriver);
                }
            }
        }
        return null;
    }
}
