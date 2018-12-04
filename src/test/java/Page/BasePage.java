package Page;

import org.openqa.selenium.WebDriver;

abstract class  BasePage {
    protected WebDriver webDriver;

    abstract boolean isPageLoaded();
}
