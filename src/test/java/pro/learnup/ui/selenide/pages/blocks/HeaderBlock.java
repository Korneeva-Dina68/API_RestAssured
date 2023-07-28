package pro.learnup.ui.selenide.pages.blocks;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pro.learnup.ui.selenide.pages.AccountPage;
import pro.learnup.ui.selenide.pages.CartPage;
import pro.learnup.ui.selenide.pages.PhonesPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HeaderBlock {
    @Step("Авторизоваться пользователем {login} {password}")
    public PhonesPage login(String login, String password) {
        $x("//button[.='LOGIN']").click();
        $x("//input[contains(@id, 'Username')]").sendKeys(login);
        $x("//input[contains(@id, 'Password')]").sendKeys(password);
        $x("//button[.='Submit']").click();
        $(byText("LOGOUT")).shouldBe(Condition.visible);
        return page(PhonesPage.class);
    }
    @Step("Нажать кнопку аккаунт")
    public AccountPage clickAccount() {
        $x("//span[text()='ACCOUNT']").click();
        return page(AccountPage.class);
    }
    @Step("Перейти в корзину")
    public CartPage goToCart() {
        $x(".//a[.='CART']").click();
        return page(CartPage.class);
    }
    @Step("Нажать кнопку LOGOUT")
    public PhonesPage clickLOGOUT() throws InterruptedException {
        $x("//span[text()='LOGOUT']").click();
        $(byText("LOGIN")).shouldBe(Condition.visible);
        return page(PhonesPage.class);
    }

}
