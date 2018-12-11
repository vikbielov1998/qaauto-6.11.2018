package Page;


import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.ArrayList;
import java.util.List;

public class RequestPasswordResetSubmitPage extends BasePage {

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return //webDriver.getTitle().contains("Пожалуйста проверьте свой email")
                 webDriver.getCurrentUrl().contains("checkpoint/rp/request-password-reset-submit");
    }

    /*public SetNewPasswordPage navigateToLinkFromEmailMine() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "testvikbielov@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        int index = message.indexOf("Reset my password");
        String[] messageArr = message.split("");
        List<Integer> indexArr = new ArrayList<Integer>();
        for (int i = 0; i < index; i++){
            if ((messageArr[i] == "a") && (messageArr[i+1] == " ") && (messageArr[i+2] == "h") && (messageArr[i+3] == "r") && (messageArr[i+4] == "e") && (messageArr[i+5] == "f") && (messageArr[i+6] == "=")){
                indexArr.add(i);
            }
        }
        int startIndex = indexArr.get(indexArr.size() - 1) + 8;
        System.out.println(startIndex);
        String link = "";
        while (messageArr[startIndex] != "\""){
            link += messageArr[startIndex];
            startIndex++;
        }
        String newLink = link.replace("&amp;", "&");
        webDriver.get(newLink);

        return new SetNewPasswordPage(webDriver);
    }*/

    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "testvikbielov@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(message, "href=\"", "\" style=").replace("amp;", "");
        webDriver.get(resetPasswordLink);
        return new SetNewPasswordPage(webDriver);
    }
}
