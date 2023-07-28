package pro.learnup.ui.pageobject.pagesWB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductGroupPageWB extends BasePageWB {
    public ProductGroupPageWB(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить результат поиска через поисковую строку")
    public ProductGroupPageWB checkingResultSearch(String product) {
        assertThat(webDriver.findElement(By.xpath("//h1[@class='searching-results__title']")).getText())
                .containsIgnoringCase(product);
        return ProductGroupPageWB.this;
    }

    @Step("Выбрать товар после поиска через поисковую строку")
    public ProductPageWB selectProduct() {
        webDriver.findElement(By.xpath("//div[@class='product-card-list']//article[1]")).click();
        return new ProductPageWB(webDriver);
    }

    @Step("Проверить результат поиска через бургер меню")
    public ProductGroupPageWB checkSectionCatalogBurgerMenu() {
        assertThat(webDriver.findElement(By.className("catalog-title-wrap")).getText())
                .containsIgnoringCase("Смарт часы и аксессуары");
        return ProductGroupPageWB.this;
    }
}
