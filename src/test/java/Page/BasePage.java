package Page;

import org.openqa.selenium.WebDriver;
import util.GMailService;

abstract class  BasePage {
    protected WebDriver webDriver;

    protected static GMailService gMailService;
    public abstract boolean isPageLoaded();
}
