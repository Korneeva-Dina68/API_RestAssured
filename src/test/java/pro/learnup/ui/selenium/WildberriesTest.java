package pro.learnup.ui.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import static org.assertj.core.api.Assertions.assertThat;

public class WildberriesTest extends BaseTest {

    @Test
    void searchBarTest() throws InterruptedException {// Ищем "самокат детский" через поисковую строку и добавляем в корзину
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//input[@class='search-catalog__input j-wba-header-item']")).sendKeys("самокат", Keys.ENTER);

        assertThat(webDriver.findElement(By.xpath("//h1[@class='searching-results__title']")).getText())
                .containsIgnoringCase("самокат");//Проверяем, что открылась страница с самокатами

        webDriver.findElement(By.xpath("//div[@class='product-card-list']//article[1]//div/a")).click();

        webDriver.findElement(By.xpath("//div[@class='product-page__aside-sticky']//span[text()='Добавить в корзину']")).click();
        webDriver.findElement(By.xpath("//a[@data-wba-header-name='Cart']")).click();

        assertThat(webDriver.findElement(By.xpath("//h1[@class='basket-section__header active']")).getText()).
                containsIgnoringCase("Корзина");//Проверяем, что мы перешли в корзину
        assertThat(webDriver.findElement(By.xpath("//div[@class='list-item__good-info good-info']")).getText())
                .containsIgnoringCase("Самокат");//Проверяем, что именно САМОКАТ у нас в корзине

    }

    @Test
    void burgerMenuTest() throws InterruptedException { //Ищем смарт часы через меню "бургер" и добавляем в корзину
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//button[@aria-label='Навигация по сайту']")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//a[@href='https://www.wildberries.ru/catalog/elektronika']")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//div[@class='menu-catalog']//a[text()='Смарт-часы и браслеты']")).click();
        Thread.sleep(3000);
        assertThat(webDriver.findElement(By.className("catalog-title-wrap")).getText())
                .containsIgnoringCase("Смарт часы и аксессуары");//Проверяем, что открылась нужная страница
        webDriver.findElement(By.xpath("//div[@class='product-card-list']//article[1]")).click();

        assertThat(webDriver.findElement(By.className("product-page__header-wrap")).getText())
                .containsIgnoringCase("смарт часы");//Проверяем, что открылась страница со смарт часами
        webDriver.findElement(By.xpath("//div[@class='product-page__aside-container j-price-block']//span[text()='Добавить в корзину']")).click();
        webDriver.findElement(By.xpath("//a[@data-wba-header-name='Cart']")).click();

        assertThat(webDriver.findElement(By.xpath("//h1[@class='basket-section__header active']")).getText()).
                containsIgnoringCase("Корзина");//Проверяем, что мы перешли в корзину
        assertThat(webDriver.findElement(By.xpath("//div[@class='list-item__good-info good-info']")).getText())
                .containsIgnoringCase("Смарт часы");//Проверяем, что смарт часы добавлены в корзину

    }

    @Test
    void favouritesTest() throws InterruptedException { // Ищем товар через поисковую строку и добавляем в "избранное"
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//input[@class='search-catalog__input j-wba-header-item']")).sendKeys("швабра", Keys.ENTER);

        assertThat(webDriver.findElement(By.xpath("//h1[@class='searching-results__title']")).getText())
                .containsIgnoringCase("По запросу «швабра» найдено");//Проверяем, что открылась нужная страница

        webDriver.findElement(By.xpath("//div[@class='product-card-list']//article[1]//div/a")).click();

        assertThat(webDriver.findElement(By.className("product-page__header-wrap")).getText())
                .containsIgnoringCase("швабра");//Проверяем, что открылась страница со шваброй
        webDriver.findElement(By.xpath("//div[@class='product-page__aside-container j-price-block']//button[@aria-label='Добавить в избранное']")).click();

        //так как пользователь не авторизован, при добавлении товара в "избранное", должна появиться форма для авторизации
        assertThat(webDriver.findElement(By.className("sign-in-page__wrap")).getText()).contains("Войти или создать профиль");
    }

    @Test
    void cleaningTrashTest() throws InterruptedException { //Запускаем метод с добавлением товара в корзину и удаляем его оттуда
        webDriver.get("https://www.wildberries.ru/");
        Thread.sleep(3000);

        searchBarTest();
        webDriver.findElement(By.xpath("//div[@class='list-item__btn btn']//button[@class='btn__del j-basket-item-del']")).click();
        Thread.sleep(2000);
        //Проверяем, что корзина пустая
        assertThat(webDriver.findElement(By.className("basket-empty__wrap")).getText()).contains("В корзине пока пусто");
        Thread.sleep(3000);
    }
}
