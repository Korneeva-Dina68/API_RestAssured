package pro.learnup.ui.selenide.pagesWB;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketPageWB extends BasePageWB {


    @Step("Проверить, что мы находимся в корзине")
    public BasketPageWB checkingLocationBasket() {
        $x("//h1[@class='basket-section__header active']")
                .shouldBe(Condition.visible)
                        .shouldBe(Condition.text("Корзина"));
        return this;
    }

    @Step("Проверить, что в корзине выбранный товар")
    public BasketPageWB checkProductInBasket(String product) {
        $x("//div[@class='list-item__good-info good-info']")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(product));
        return this;
    }

    @Step("Удалить товар из корзины")
    public BasketPageWB deleteProductBasket() throws InterruptedException {
        $x("//div[@class='list-item__btn btn']//button[@class='btn__del j-basket-item-del']").click();
        Thread.sleep(2000);
        return page(BasketPageWB.class);
    }

    @Step("Проверить, что корзина пустая")
    public BasketPageWB checkBasketIsEmpty() {
        $(By.className("basket-empty__wrap"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("В корзине пока пусто"));
        return this;
    }
}
