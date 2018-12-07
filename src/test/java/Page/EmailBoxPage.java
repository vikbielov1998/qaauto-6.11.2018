package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmailBoxPage {

    private WebDriver webDriver;

    @FindBy (xpath = "//tr[contains(@class, 'zA')]")
    private List<WebElement> emailList;

    @FindBy (linkText = "Reset my password")                              //xpath = "//*/div[1]/table/tbody/tr/td/center/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/p/a"
    private WebElement resetLink;

    public EmailBoxPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public FinalResetPage findCorrectMail() {
        for(int i = 0; i < emailList.size(); i++){
            if(emailList.get(i).getText().contains("LinkedIn")){
                emailList.get(i).click();
                if (resetLink.isDisplayed()) {
                    resetLink.click();
                    return new FinalResetPage(webDriver);
                }
            }
        }
        return null;
    }
}
