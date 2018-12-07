package Test;

import Page.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseTest {

    /**
     * Precondition:
     * - open browser;
     * - navigate to linkeddin.com;
     * - login with invalid data;
     *
     * Scenario:
     * - Verify login submit page is loaded;
     * - Click link "Forgot your password;
     * - Verify reset password page is loaded;
     * - Enter email;
     * - Verify success message is displayed;
     * - Go to mail;
     * - Open message from LinkedIn;
     * - Click the link "Reset my password";
     * - Enter new password;
     * - Confirm password;
     * - Go to home page.
     */

    @Test
    public void resetPasswordTest (){
        LoginSubmitPage loginSubmitPage = loginPage.login("testvikbielov@gmail.com", "wrongpass");

        ResetPasswordPage resetPasswordPage = loginSubmitPage.clickForgotPasswordLink();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(), "Reset password page is not loaded");

        resetPasswordPage.enterEmail("testvikbielov@gmail.com");
        Assert.assertTrue(resetPasswordPage.isEmailSent(), "Email was not send");

        String userMail = "testvikbielov";
        String userPass = "112233qweqwedbrnjh";
        webDriver.get("https://accounts.google.com/ServiceLogin?");
        EmailLoginPage emailLoginPage = new EmailLoginPage(webDriver);

        EmailBoxPage emailBoxPage = emailLoginPage.login(userMail, userPass);

        FinalResetPage finalResetPage = emailBoxPage.findCorrectMail();

        HomePage homePage = finalResetPage.enterNewPassword();

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }
}
