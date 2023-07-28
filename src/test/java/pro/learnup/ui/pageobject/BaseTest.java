package pro.learnup.ui.pageobject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pro.learnup.ui.pageobject.listener.EventAllureListener;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected EventFiringWebDriver webDriver;
    protected WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() throws InterruptedException {
        webDriver = new EventFiringWebDriver(WebDriverManager.chromedriver().create());
        webDriver.register(new EventAllureListener());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 3);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}

