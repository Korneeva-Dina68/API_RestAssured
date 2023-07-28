package pro.learnup.ui.pageobject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class PhonePage extends BasePage {
    public PhonePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что открылась страница с телефоном {phoneName}")
    public PhonePage checkPhoneName (String phoneName) {
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())
                .as("Должна открыться страница с телефоном" + phoneName)
                .isEqualTo(phoneName);
        return this;
    }

    @Step("Нажать на кнопку AddToCart")
    public PhonePage clickAddToCart() {
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        return this;
    }

    @Step("Нажать на кнопку Back to catalog")
    public PhonesPage backToCatalog() {
        webDriver.findElement(By.xpath("//span[contains(text(), 'Back to catalog')]")).click();
        return new PhonesPage(webDriver);
    }
}
