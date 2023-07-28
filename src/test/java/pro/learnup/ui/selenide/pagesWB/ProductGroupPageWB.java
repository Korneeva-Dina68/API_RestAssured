package pro.learnup.ui.selenide.pagesWB;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductGroupPageWB extends BasePageWB {

    @Step("Проверить результат поиска через поисковую строку")
    public ProductGroupPageWB checkingResultSearch(String product) {
        $x("//h1[@class='searching-results__title']")
//                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(product));
        return this;
    }

    @Step("Выбрать товар после поиска через поисковую строку")
    public ProductPageWB selectProduct() {
        $x("//div[@class='product-card-list']//article[1]").click();
        return page(ProductPageWB.class);
    }

    @Step("Проверить результат поиска через бургер меню")
    public ProductGroupPageWB checkSectionCatalogBurgerMenu() {
        $(By.className("catalog-title-wrap"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Смарт часы и аксессуары"));
        return this;
    }
}
