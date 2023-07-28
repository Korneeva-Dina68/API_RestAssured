package pro.learnup.ui.pageobject;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import pro.learnup.ui.pageobject.pagesWB.AddressesPageWB;
import pro.learnup.ui.pageobject.pagesWB.MainPageWB;
import pro.learnup.ui.pageobject.pagesWB.blocksWB.HeaderBlockWB;


public class WildberriesTest extends BaseTest {
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
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        new MainPageWB(webDriver)
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
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        new MainPageWB(webDriver)
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
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        new MainPageWB(webDriver)
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
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        new MainPageWB(webDriver)
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
    void questionsAndAnswersMakingAnOrder() throws InterruptedException {
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        new HeaderBlockWB(webDriver).clickButtonAddresses();
        new AddressesPageWB(webDriver).clickQuestionsAndAnswers();
        new AddressesPageWB(webDriver).switchToFrame();
        new AddressesPageWB(webDriver).checkToBeQuestionsAndAnswers();
        new AddressesPageWB(webDriver).clickInputYourQuestionAndSendKeys(request);
        new AddressesPageWB(webDriver).clickSelectingSubqueryQuestion();
        new AddressesPageWB(webDriver).checkSelectingSubqueryQuestion(request);

    }

    @Feature("Создание запроса через боковое меню в Вопросы и ответы")
    @Description("Создаем запрос через боковое меню в Вопросы и ответы")
    @Severity(SeverityLevel.NORMAL)
    @Name("Создание запроса через боковое меню в Вопросы и ответы")
    @Test
    void questionsAndAnswersСhangeEmail() throws InterruptedException {
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        new HeaderBlockWB(webDriver).clickButtonAddresses();
        new AddressesPageWB(webDriver).clickQuestionsAndAnswers();
        new AddressesPageWB(webDriver).switchToFrame();
        new AddressesPageWB(webDriver).checkToBeQuestionsAndAnswers();
        new AddressesPageWB(webDriver).clickSideMenu();
        new AddressesPageWB(webDriver).checkSelectingSubqueryQuestion(request2);
        new AddressesPageWB(webDriver).clickHowToChangeEmail();
    }
}
