package pro.learnup.ui.pageobject.pagesWB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketPageWB extends BasePageWB {
    public BasketPageWB(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что мы находимся в корзине")
    public BasketPageWB checkingLocationBasket() {
        assertThat(webDriver.findElement(By.xpath("//h1[@class='basket-section__header active']")).getText()).
                containsIgnoringCase("Корзина");
        return BasketPageWB.this;
    }

    @Step("Проверить, что в корзине выбранный товар")
    public BasketPageWB checkProductInBasket(String product) {
        assertThat(webDriver.findElement(By.xpath("//div[@class='list-item__good-info good-info']")).getText())
                .containsIgnoringCase(product);
        return BasketPageWB.this;
    }

    @Step("Удалить товар из корзины")
    public BasketPageWB deleteProductBasket() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@class='list-item__btn btn']//button[@class='btn__del j-basket-item-del']")).click();
        Thread.sleep(2000);
        return new BasketPageWB(webDriver);
    }

    @Step("Проверить, что корзина пустая")
    public BasketPageWB checkBasketIsEmpty() {
        assertThat(webDriver.findElement(By.className("basket-empty__wrap")).getText()).contains("В корзине пока пусто");
        return BasketPageWB.this;
    }
}
