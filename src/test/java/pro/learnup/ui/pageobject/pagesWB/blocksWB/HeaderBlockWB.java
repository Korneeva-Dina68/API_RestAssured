package pro.learnup.ui.pageobject.pagesWB.blocksWB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pro.learnup.ui.pageobject.pagesWB.*;

public class HeaderBlockWB extends BaseViewWB {
    public HeaderBlockWB(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать и ввести название товара в поисковую строку")
    public ProductGroupPageWB clickAndInputSearchBar(String product) {
        webDriver.findElement(By.xpath("//input[@class='search-catalog__input j-wba-header-item']")).sendKeys(product, Keys.ENTER);
        return new ProductGroupPageWB(webDriver);
    }

    @Step("Нажать на бургер меню")
    public MainPageWB clickBurgerMenu() throws InterruptedException {
        webDriver.findElement(By.xpath("//button[@aria-label='Навигация по сайту']")).click();
        Thread.sleep(3000);
        return new MainPageWB(webDriver);
    }

    @Step("Нажать на кнопку Корзина")
    public BasketPageWB goToBasket() throws InterruptedException {
        webDriver.findElement(By.xpath("//a[@data-wba-header-name='Cart']")).click();
        Thread.sleep(2000);
        return new BasketPageWB(webDriver);
    }

    @Step("Нажать на кнопку Адреса")
    public void clickButtonAddresses() {
        webDriver.findElement(By.xpath("//div[@class='navbar-pc__item j-item-addresses']")).click();
    }
}
