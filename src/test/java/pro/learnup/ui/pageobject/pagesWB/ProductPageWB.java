package pro.learnup.ui.pageobject.pagesWB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageWB extends BasePageWB {
    public ProductPageWB(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что мы на странице конкретного товара")
    public ProductPageWB checkSpecificProduct(String product) {
        assertThat(webDriver.findElement(By.className("product-page__header-wrap")).getText())
                .containsIgnoringCase(product);
        return ProductPageWB.this;
    }

    @Step("Добавить товар в корзину")
    public ProductPageWB addInCart() {
        webDriver.findElement(By.xpath("//div[@class='product-page__aside-sticky']//span[text()='Добавить в корзину']")).click();
        return new ProductPageWB(webDriver);
    }

    @Step("Добавить товар в Избранное")
    public MainPageWB addToFavoritesProduct() {
        webDriver.findElement(By.xpath("//div[@class='product-page__aside-container j-price-block']//button[@aria-label='Добавить в избранное']")).click();
        return new MainPageWB(webDriver);
    }
}
