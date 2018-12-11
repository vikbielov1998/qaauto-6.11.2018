package Test;

import Page.HomePage;
import Page.RequestPasswordResetPage;
import Page.RequestPasswordResetSubmitPage;
import Page.SetNewPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**Class for successful password reset test
 *
 */
public class ResetPassTest extends BaseTest {

    /**Test for successful password reset
     *
     */
    @Test
    public void successfulResetPasswordTest(){

        String userEmail = "testvikbielov@gmail.com";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page isn't loaded");

        RequestPasswordResetPage requestPasswordResetPage = loginPage.clickForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "Request password page isn't loaded");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "Request password reset submit page isn't loaded");

        SetNewPasswordPage setNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(setNewPasswordPage.isPageLoaded(), "Page is not loaded");

        HomePage homePage = setNewPasswordPage.enterNewPassword();
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }
}
