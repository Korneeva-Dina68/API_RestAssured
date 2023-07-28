package pro.learnup.ui.pageobject.pagesWB;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;


public class AddressesPageWB extends BasePageWB{
    public AddressesPageWB(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать кнопку Вопросы и ответы")
    public void clickQuestionsAndAnswers() {
        webDriver.findElement(By.xpath("//li[@class='service-menu__item']//a[text()='Вопросы и ответы']")).click();
    }

    @Step("Переключиться на frame")
    public void switchToFrame() {
        webDriver.switchTo().frame("pageInfoIfr");
    }

    @Step("Проверить, что мы на странице Вопросов и ответов")
    public void checkToBeQuestionsAndAnswers() {
        assertThat(webDriver.findElement(By.xpath("//div[@id='questions-answers']/h1")).getText())
                .containsIgnoringCase("Вопросы и ответы");
    }

    @Step("Ввести запрос в строку ввода")
    public void clickInputYourQuestionAndSendKeys(String request) {
        webDriver.findElement(By.xpath("//div[@class='faq-search-block']//input[@class='faq-search-input']")).sendKeys(request);
    }

    @Step("Нажать на выбранный вариант подстроки запроса")
    public void clickSelectingSubqueryQuestion() {
        webDriver.findElement(By.xpath("//ul[@class='faq-result-list j-ul-faq-search-results']//span[text()='Оформление заказа']")).click();
    }

    @Step("Проверить, что мы перешли в выбранный вариант подстроки запроса")
    public void checkSelectingSubqueryQuestion(String request) {
        assertThat(webDriver.findElement(By.xpath("//div[@class='faq-contain-item j-faq-contain-item selected']//h1")).getText())
                .containsIgnoringCase(request);
    }

    @Step("Нажать в боковом меню Управление личным кабинетом")
    public void clickSideMenu() {
        webDriver.findElement(By.xpath("//div[@class='faq-menu j-faq-menu']//span[text()='Управление личным кабинетом']")).click();
    }

    @Step("Нажать Как изменить email в подстроке Управления личным кабинетом")
    public void clickHowToChangeEmail() {
        webDriver.findElement(By.xpath("//div[@class='faq-contain-item j-faq-contain-item selected']//h2[text()='Как изменить e-mail?']")).click();
    }
}
