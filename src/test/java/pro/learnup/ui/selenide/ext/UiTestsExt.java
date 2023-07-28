package pro.learnup.ui.selenide.ext;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension. AfterEachCallback;
import org.junit.jupiter.api.extension. BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class UiTestsExt implements AfterEachCallback, BeforeEachCallback {
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
//        Selenide.closeWebDriver();
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        WebDriverRunner.clearBrowserCache();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Configuration.startMaximized=true;
        SelenideLogger.addListener("Allure", new AllureSelenide().screenshots(true).savePageSource(true));
    }
}
