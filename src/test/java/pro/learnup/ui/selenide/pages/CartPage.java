package pro.learnup.ui.selenide.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {

    private SelenideElement emptyCartButton = $(By.xpath("//button[.='Empty cart']"));
    private SelenideElement cartItems = $(By.className("cart-items"));


    @Step("Проверить, что в корзине только телефоны {phoneName}")
    public CartPage checkCartContainExactly(String... phoneName) {
        cartItems.$$x(".//table//tbody/tr//a")
                .shouldHave(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(phoneName));
        return this;
    }

    @Step("Удалить из корзины телефон {phoneName}")
    public CartPage deletePhone(String phoneName) {
        ElementsCollection phones = $$(By.className("cart-items"));
        phones.stream()
                .filter(el->el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath("//button[contains(@title,'Remove this item from the cart')]"))
                .click();
        return page(CartPage.class);
    }

    @Step("Удалить из корзины все телефоны")
    public CartPage deleteAllPhoneYes() {
        emptyCartButton.click();
        $x("//span[contains(text(), 'Yes')]").click();
        $(By.className("cart-items")).shouldBe(Condition.visible);
        return page(CartPage.class);
    }

    @Step("Отменить удаление телефонов")
    public CartPage deleteAllPhoneCancel() {
        emptyCartButton.click();
        $x("//span[contains(text(), 'Cancel')]").click();
        return page(CartPage.class);
    }

    @Step("Проверить, что корзина пустая")
    public CartPage checkNoItemsCart() {
        cartItems.shouldBe(visible)
                .shouldBe(Condition.exactText("No items in the cart."));
        return page(CartPage.class);
    }

    @Step("Нажать на кнопку CheckOut")
    public CartPage clickCheckOut() {
        $x("//button[.='Checkout']").click();
        return page(CartPage.class);
    }

    @Step("Нажать на кнопку Confirm")
    public CartPage clickConfirm() {
        $x("//button[.='Confirm']").click();
        return page(CartPage.class);
    }

    @Step("Проверить, что покупка совершена успешно")
    public CartPage checkThatCheckOutSuccessful() {
        $(byText("Your order has been received. The items you've ordered will be sent to your address.")).shouldBe(Condition.visible);
        return page(CartPage.class);
    }
    @Step("Нажать на кнопку OK")
    public CartPage clickOK() {
        $x("//button[.='OK']").click();
        return page(CartPage.class);
    }
}
