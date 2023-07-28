package pro.learnup.ui.pageobject.pagesWB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageWB extends BasePageWB {
    public MainPageWB(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на каталог бургер меню")
    public MainPageWB clickCatalogBurgerMenu() throws InterruptedException {
        webDriver.findElement(By.xpath("//a[@href='https://www.wildberries.ru/catalog/elektronika']")).click();
        Thread.sleep(3000);
        return new MainPageWB(webDriver);
    }

    @Step("Нажать на секцию каталога бургер меню")
    public ProductGroupPageWB clickSectionCatalogBurgerMenu() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@class='menu-catalog']//a[text()='Смарт-часы и браслеты']")).click();
        Thread.sleep(3000);
        return new ProductGroupPageWB(webDriver);
    }

    @Step("Проверить, что мы на странице Профиля")
    public MainPageWB checkProfileRegistration() {
        assertThat(webDriver.findElement(By.className("sign-in-page__wrap")).getText()).contains("Войти или создать профиль");
        return MainPageWB.this;
    }

}
