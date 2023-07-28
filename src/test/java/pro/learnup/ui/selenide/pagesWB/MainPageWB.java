package pro.learnup.ui.selenide.pagesWB;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageWB extends BasePageWB {

    @Step("Нажать на каталог бургер меню")
    public MainPageWB clickCatalogBurgerMenu() throws InterruptedException {
        $x("//a[@href='https://www.wildberries.ru/catalog/elektronika']").click();
        Duration.ofSeconds(3000);
        return page(MainPageWB.class);
    }

    @Step("Нажать на секцию каталога бургер меню")
    public ProductGroupPageWB clickSectionCatalogBurgerMenu() throws InterruptedException {
        $x("//div[@class='menu-catalog']//a[text()='Смарт-часы и браслеты']").click();
        Thread.sleep(3000);
        return page(ProductGroupPageWB.class);
    }

    @Step("Проверить, что мы на странице Профиля")
    public MainPageWB checkProfileRegistration() {
        $(By.className("sign-in-page__wrap"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Войти или создать профиль"));
        return this;
    }

}
