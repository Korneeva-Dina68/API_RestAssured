package pro.learnup.ui.selenide.pagesWB;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageWB extends BasePageWB {

    @Step("Проверить, что мы на странице конкретного товара")
    public ProductPageWB checkSpecificProduct(String product) {
        $(By.className("product-page__header-wrap"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(product));
        return this;
    }

    @Step("Добавить товар в корзину")
    public ProductPageWB addInCart() {
        $x("//div[@class='product-page__aside-sticky']//span[text()='Добавить в корзину']").click();
        return page(ProductPageWB.class);
    }

    @Step("Добавить товар в Избранное")
    public MainPageWB addToFavoritesProduct() {
        $x("//div[@class='product-page__aside-container j-price-block']//button[@aria-label='Добавить в избранное']").click();
        return page(MainPageWB.class);
    }
}
