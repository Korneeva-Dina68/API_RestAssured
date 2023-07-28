package pro.learnup.ui.pageobject.pagesWB;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pro.learnup.ui.pageobject.pagesWB.blocksWB.HeaderBlockWB;

public class BasePageWB extends BaseViewWB {
    @Getter
    private HeaderBlockWB headerBlockWB = new HeaderBlockWB(webDriver);
    public BasePageWB(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}
