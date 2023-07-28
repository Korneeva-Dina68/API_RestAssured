package pro.learnup.ui.bdd;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pro.learnup.ui.selenide.pages.CartPage;
import pro.learnup.ui.selenide.pages.PhonePage;
import pro.learnup.ui.selenide.pages.PhonesPage;
import pro.learnup.ui.selenide.pages.blocks.HeaderBlock;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyStepdefs {
    @Дано("Пользователь {string} {string} авторизован")
    public void login(String arg0, String arg1) {
        open("http://localhost:3000/",PhonesPage .class)
                .getHeaderBlock()
                .login(arg0, arg1);
    }

    @Когда("Пользователь выбирает телефон {string}")
    public void selectPhone(String arg0) {
        page(PhonesPage.class).selectPhone(arg0);
    }

    @Тогда("Пользователь находится на странице телефона {string}")
    public void checkPhoneName(String arg0) {
        page(PhonePage.class).checkPhoneName(arg0);
    }

    @Когда("Пользователь нажимает на кнопку {string}")
    public void clickButton(String arg0) {
        $x("//button[.='" + arg0 + "']").click();
    }

    @И("Пользователь переходит в корзину")
    public void goToCart() {
        new HeaderBlock().goToCart();
    }

    @То("В корзине только телефон {string}")
    public void checkCartContainExactly(String arg0) {
        new CartPage().checkCartContainExactly(arg0);
    }

    @Тогда("Отображается текст об успешном оформлении заказа")
    public void checkThatCheckOutSuccessful() {
        page(CartPage.class).checkThatCheckOutSuccessful();
    }

    @Дано("Открыта базовая страница сайта")
    public void openBasePage() {
        open("http://localhost:3000/");
    }

    @И("Пользователь вводит значение {string} в строку {string}")
    public void input(String arg0, String arg1) {
        $x("//input[contains(@id, '"+ arg1 +"')]").sendKeys(arg0);
    }

    @Тогда("отображается кнопка {string}")
    public void buttonIsVisible(String arg0) {
        $(byText(arg0)).shouldBe(Condition.visible);
    }

    @Когда("Пользователь нажимает категорию {string}")
    public void clickСategory(String arg0) {
        $x("//div[contains(text(),'"+ arg0 +"')]").click();
    }

    @И("Пользователь нажимает подкатегорию {string}")
    public void clickSubcategory(String arg0) {
        $x("//input[@value='"+ arg0 +"']").click();
    }

    @Тогда("На странице находятся телефоны только с параметрами {string}")
    public void checkFilterPhone(String arg0) {
        ElementsCollection brandPhones = $$x("//div[@class='content-left']");
        brandPhones.forEach(p-> {
            if (p.getText().contains(arg0)) System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });
    }

    @Тогда("На странице находятся все телефоны")
    public void checkClearFilter() {
        $x("//div[@class='products-found']").shouldBe(Condition.visible)
                .shouldBe(Condition.text("10"));
    }

    @И("Пользователь нажимает кнопку {string}")
    public void backToCatalog(String arg0) {
        $x("//span[text()='"+ arg0 +"']").click();
    }

    @То("В корзине только телефоны {string}, {string}, {string}")
    public void checkCartContainExactly(String arg0, String arg1, String arg2) {
        $(By.className("cart-items")).$$x(".//table//tbody/tr//a")
                .shouldHave(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(arg0, arg1, arg2));
    }

    @Когда("Пользователь нажимает на крестик у телефона {string}")
    public void deletePhone(String arg0) {
        ElementsCollection phones = $$(By.className("cart-items"));
        phones.stream()
                .filter(el->el.getText().contains(arg0))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath("//button[contains(@title,'Remove this item from the cart')]"))
                .click();
    }

    @Тогда("Корзина будет пустая")
    public void checkNoItemsCart() {
        $(By.className("cart-items")).shouldBe(visible)
                .shouldBe(Condition.exactText("No items in the cart."));
    }

    @И("Пользователь нажимает удаление {string}")
    public void deleteAllPhoneYes(String arg0) {
        $(By.xpath("//button[.='Empty cart']")).click();
        $x("//span[contains(text(), 'Yes')]").click();
        $(By.className("cart-items")).shouldBe(Condition.visible);
    }

    @Тогда("Пользователь находится на странице {string}")
    public void пользовательНаходитсяНаСтранице(String arg0) {
        $(byText(arg0)).shouldBe(Condition.visible);
    }

    @Когда("Пользователь удаляет данные и вводит значение {string} в строку {string}")
    public void пользовательУдаляетДанныеИВводитЗначениеВСтроку(String arg0, String arg1) {
        $(By.xpath("//input[contains(@id, '"+ arg1 +"')]")).click();
        $(By.xpath("//input[contains(@id, '"+ arg1 +"')]")).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $(By.xpath("//input[contains(@id, '"+ arg1 +"')]")).sendKeys(arg0);
    }

    @Когда("Пользователь нажимает редактирование аккаунта {string}")
    public void пользовательНажимаетРедактированиеАккаунта(String arg0) {
        $x("//button[@aria-label='"+ arg0 +"']").click();
    }
}
