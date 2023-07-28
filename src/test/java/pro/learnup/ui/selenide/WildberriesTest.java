package pro.learnup.ui.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import pro.learnup.ui.selenide.ext.UiTestsExt;
import pro.learnup.ui.selenide.pagesWB.AddressesPageWB;
import pro.learnup.ui.selenide.pagesWB.MainPageWB;
import pro.learnup.ui.selenide.pagesWB.blocksWB.HeaderBlockWB;
import org.junit.jupiter.api.extension.ExtendWith;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(UiTestsExt.class)
public class WildberriesTest extends UiTestsExt {
    String product = "самокат";
    String product2 = "швабра";
    String product3 = "смарт часы";
    String request = "оформление заказа";
    String request2 = "Управление личным кабинетом";


    @Feature("Поиск товара и добавление в корзину")
    @Description("Ищем {0} через поисковую строку и добавляем в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Поиск товара и добавление в корзину")
    @Test
    void searchBarTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");
        Thread.sleep(3000);
                new MainPageWB()
                .getHeaderBlockWB()
                .clickAndInputSearchBar(product)
                .checkingResultSearch(product)
                .selectProduct()
                .checkSpecificProduct(product)
                .addInCart()
                .getHeaderBlockWB()
                .goToBasket()
                .checkingLocationBasket()
                .checkProductInBasket(product);
    }

    @Feature("Поиск товара и добавление в корзину")
    @Description("Ищем {0} через меню бургер и добавляем в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Поиск товара и добавление в корзину")
    @Test
    void burgerMenuTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");
        Thread.sleep(3000);
            new MainPageWB()
                .getHeaderBlockWB()
                .clickBurgerMenu()
                .clickCatalogBurgerMenu()
                .clickSectionCatalogBurgerMenu()
                .checkSectionCatalogBurgerMenu()
                .selectProduct()
                .checkSpecificProduct(product3)
                .addInCart()
                .getHeaderBlockWB()
                .goToBasket()
                .checkingLocationBasket()
                .checkProductInBasket(product3);
    }

    @Feature("Поиск товара и добавление в избранное")
    @Description("Ищем {0} через поисковую строку и добавляем в избранное")
    @Severity(SeverityLevel.NORMAL)
    @Name("Поиск товара и добавление в избранное")
    @Test
    void addFavouritesTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");
        Thread.sleep(3000);
            new MainPageWB()
                .getHeaderBlockWB()
                .clickAndInputSearchBar(product2)
                .checkingResultSearch(product2)
                .selectProduct()
                .checkSpecificProduct(product2)
                .addToFavoritesProduct()
                .checkProfileRegistration();
    }

    @Feature("Добавление и удаление товара из корзины")
    @Description("Добавляем {0} в корзину и удаляем его из корзины")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Добавление и удаление товара из корзины")
    @Test
    void cleaningBasketTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");
        Thread.sleep(3000);
            new MainPageWB()
                .getHeaderBlockWB()
                .clickAndInputSearchBar(product)
                .checkingResultSearch(product)
                .selectProduct()
                .checkSpecificProduct(product)
                .addInCart()
                .getHeaderBlockWB()
                .goToBasket()
                .checkingLocationBasket()
                .checkProductInBasket(product)
                .deleteProductBasket()
                .checkBasketIsEmpty();
    }

    @Feature("Создание запроса через поисковую строку в Вопросы и ответы")
    @Description("Создаем запрос через поисковую строку Оформление заказа в Вопросы и ответы")
    @Severity(SeverityLevel.NORMAL)
    @Name("Создание запроса через поисковую строку в Вопросы и ответы")
    @Test
    void questionsAndAnswersMakingAnOrderTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");

        new HeaderBlockWB().clickButtonAddresses();
        new AddressesPageWB().checkToBeAddress();
        new AddressesPageWB().clickQuestionsAndAnswers();
        new AddressesPageWB().switchToFrame();
        new AddressesPageWB().checkToBeQuestionsAndAnswers();
        new AddressesPageWB().clickInputYourQuestionAndSendKeys(request);
        new AddressesPageWB().clickSelectingSubqueryQuestion();
        new AddressesPageWB().checkSelectingSubqueryQuestion(request);

    }

    @Feature("Создание запроса через боковое меню в Вопросы и ответы")
    @Description("Создаем запрос через боковое меню в Вопросы и ответы")
    @Severity(SeverityLevel.NORMAL)
    @Name("Создание запроса через боковое меню в Вопросы и ответы")
    @Test
    void questionsAndAnswersСhangeEmailTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");

        new HeaderBlockWB().clickButtonAddresses();
        new AddressesPageWB().checkToBeAddress();
        new AddressesPageWB().clickQuestionsAndAnswers();
        new AddressesPageWB().switchToFrame();
        new AddressesPageWB().checkToBeQuestionsAndAnswers();
        new AddressesPageWB().clickSideMenu();
        new AddressesPageWB().checkSelectingSubqueryQuestion(request2);
        new AddressesPageWB().clickHowToChangeEmail();
    }

    @Feature("Проверка актуальности Правила пользования торговой площадкой")
    @Description("Проверяем, работает ли запрос Правила пользования торговой площадкой")
    @Severity(SeverityLevel.NORMAL)
    @Name("Правила пользования торговой площадкой")
    @Test
    void termsOfUseOfTheTradingPlatformTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");

        $x("//div[@class='navbar-pc__item j-item-addresses']").click();
        Selenide.sleep(2000);
        $x("//div[@class='free-shipping-banner']/h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Доставка"));

        $x("//li[@class='service-menu__item']//a[text()='Правила пользования торговой площадкой']").click();

        Selenide.switchTo().frame("pageInfoIfr");

        $x("//div[@class='page-oferta wrap-helper']/h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Правила пользования торговой площадкой"));
    }

    @Feature("Проверка актуальности Правила пользования торговой площадкой")
    @Description("Проверяем, работает ли запрос Правила пользования торговой площадкой")
    @Severity(SeverityLevel.NORMAL)
    @Name("Правила пользования торговой площадкой")
    @Test
    void requestPaymentMethodsTest() throws InterruptedException {
        Configuration.startMaximized=true;
        open("https://www.wildberries.ru/");

        $x("//div[@class='navbar-pc__item j-item-addresses']").click();
        Selenide.sleep(2000);
        $x("//div[@class='free-shipping-banner']/h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Доставка"));

        $x("//li[@class='service-menu__item']//a[text()='Способы оплаты']").click();

        Selenide.switchTo().frame("pageInfoIfr");

        $x("//div[@class='payment-method wrap-helper']/h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Способы оплаты"));
    }
}
