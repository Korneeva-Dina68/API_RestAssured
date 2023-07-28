package pro.learnup.ui.selenide.pagesWB.blocksWB;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import pro.learnup.ui.selenide.pagesWB.BasketPageWB;
import pro.learnup.ui.selenide.pagesWB.MainPageWB;
import pro.learnup.ui.selenide.pagesWB.ProductGroupPageWB;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class HeaderBlockWB {

    @Step("Нажать и ввести название товара в поисковую строку")
    public ProductGroupPageWB clickAndInputSearchBar(String product) throws InterruptedException {
        $x("//input[@class='search-catalog__input j-wba-header-item']").sendKeys(product, Keys.ENTER);
        return page(ProductGroupPageWB.class);
    }

    @Step("Нажать на бургер меню")
    public MainPageWB clickBurgerMenu() {
        $x("//button[@aria-label='Навигация по сайту']").click();
        Selenide.sleep(3000);
        return page(MainPageWB.class);
    }

    @Step("Нажать на кнопку Корзина")
    public BasketPageWB goToBasket() {
        $x("//a[@data-wba-header-name='Cart']").click();
        Selenide.sleep(2000);
        return page(BasketPageWB.class);
    }

    @Step("Нажать на кнопку Адреса")
    public void clickButtonAddresses() {
        $x("//div[@class='navbar-pc__item j-item-addresses']").click();
        Selenide.sleep(2000);
    }
}
