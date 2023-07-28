package pro.learnup.ui.selenide.pagesWB;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;


public class AddressesPageWB extends BasePageWB {

    @Step("Проверить, что мы на странице Адреса")
    public void checkToBeAddress() {
        $x("//div[@class='free-shipping-banner']/h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Доставка"));
    }

    @Step("Нажать кнопку Вопросы и ответы")
    public void clickQuestionsAndAnswers() {
        $x("//li[@class='service-menu__item']//a[text()='Вопросы и ответы']").click();
    }

    @Step("Переключиться на frame")
    public void switchToFrame() {
        Selenide.switchTo().frame("pageInfoIfr");
    }

    @Step("Проверить, что мы на странице Вопросов и ответов")
    public void checkToBeQuestionsAndAnswers() {
        $x("//div[@id='questions-answers']/h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text("Вопросы и ответы"));
    }

    @Step("Ввести запрос в строку ввода в Вопросах и ответах")
    public void clickInputYourQuestionAndSendKeys(String request) {
        $x("//div[@class='faq-search-block']//input[@class='faq-search-input']").sendKeys(request);
    }

    @Step("Нажать на выбранный вариант подстроки запроса")
    public void clickSelectingSubqueryQuestion() {
        $x("//ul[@class='faq-result-list j-ul-faq-search-results']//span[text()='Оформление заказа']").click();
    }

    @Step("Проверить, что мы перешли в выбранный вариант подстроки запроса")
    public void checkSelectingSubqueryQuestion(String request) {
        $x("//div[@class='faq-contain-item j-faq-contain-item selected']//h1")
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(request));
    }

    @Step("Нажать в боковом меню Управление личным кабинетом")
    public void clickSideMenu() {
        $x("//div[@class='faq-menu j-faq-menu']//span[text()='Управление личным кабинетом']").click();
    }

    @Step("Нажать Как изменить email в подстроке Управления личным кабинетом")
    public void clickHowToChangeEmail() {
        $x("//div[@class='faq-contain-item j-faq-contain-item selected']//h2[text()='Как изменить e-mail?']").click();
    }
}
