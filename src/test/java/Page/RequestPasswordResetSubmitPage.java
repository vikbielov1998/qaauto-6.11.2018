package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestPasswordResetSubmitPage extends BasePage {

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getTitle().contains("Пожалуйста проверьте свой email") && webDriver.getCurrentUrl().contains("checkpoint/rp/request-password-reset-submit");
    }

    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "testvikbielov@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        return new SetNewPasswordPage(webDriver);
    }
}
