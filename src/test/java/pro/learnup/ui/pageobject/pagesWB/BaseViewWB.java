package pro.learnup.ui.pageobject.pagesWB;

import org.openqa.selenium.WebDriver;

public class BaseViewWB {
    protected WebDriver webDriver;

    public BaseViewWB(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
