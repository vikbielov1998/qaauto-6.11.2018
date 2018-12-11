package Test;

import Page.HomePage;
import Page.LoginPage;
import Page.LoginSubmitPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**Test class for login test
 *
 */
public class LoginTest extends BaseTest{

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"testvikbielov@gmail.com", "newpa$$word"},
                {"testvikbielov@GMAIL.COM", "newpa$$word"},
                {" testvikbielov@gmail.com ", "newpa$$word"}
        };
    }

    /**Test for login with valid data
     * @param userEmail valid user email
     * @param userPass valid user password
     */
    @Test(dataProvider = "validDataProvider")
    public void positiveLoginTest(String userEmail, String userPass) {
        HomePage homePage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }


    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                {"testvikbielov@gmail.com", ""},
                {"", "112233qweqwedbrnjh"},
                {"", ""}
        };
    }

    /**Test for login without entering some/both field/s
     * @param userEmail user email
     * @param userPass user email
     */
    @Test(dataProvider = "emptyDataProvider")
    public void emptyField(String userEmail, String userPass) {
        LoginPage loginPage1 = loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginPage1.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"testvikbielov@@gmail.com", "112233qweqwedbrnjh", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},                       //wrong email
                {"testvikbielov@gmail.com", "pa$$word", "", "Это неверный пароль. Повторите попытку или измените пароль."},                                             //wrong password
                {"testvikbielov@gmailcom", "112233qweqwedbrnjh", "Этот адрес эл. почты не зарегистрирован в LinkedIn.\nВозможно, вы имели в виду @gmail.com?", ""},     //email without dot
        };
    }

    /**Test for login with invalid data
     * @param userEmail user email
     * @param userPass user password
     * @param emailErrorMessage error message for email
     * @param passErrorMessage error message for password
     */
    @Test(dataProvider = "invalidDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String emailErrorMessage, String passErrorMessage) {
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded());
        Assert.assertEquals(loginSubmitPage.getUserEmailErrorMessage(), emailErrorMessage, "Wrong email error message");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), passErrorMessage, "Wrong password error message");
    }
}