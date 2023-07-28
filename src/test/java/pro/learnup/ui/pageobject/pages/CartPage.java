package pro.learnup.ui.pageobject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {
    @FindBy(className = "cart-items")
    WebElement cartItems;
    @FindBy(xpath = "//button[.='Empty cart']")
    WebElement emptyCartButton;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что в корзине только телефоны {phoneName}")
    public CartPage checkCartContainExactly(String... phoneName) {
        List<String> actualPhoneList = cartItems
                .findElements(By.xpath(".//table//tbody/tr"))
                .stream()
                .map(el->el.findElements(By.xpath("./td")).get(1).getText())
                .collect(Collectors.toList());

        assertThat(actualPhoneList).containsExactlyInAnyOrder(phoneName);
        return this;
    }

    @Step("Удалить из корзины телефон {phoneName}")
    public CartPage deletePhone(String phoneName) {
        List<WebElement> phones = webDriver.findElements(By.className("cart-items"));
        phones.stream()
                .filter(el->el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath("//button[contains(@title,'Remove this item from the cart')]"))
                .click();
        return new CartPage(webDriver);
    }

    @Step("Удалить из корзины все телефоны")
    public CartPage deleteAllPhoneYes() {
        emptyCartButton.click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Yes')]")).click();
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cart-items")));
        return new CartPage(webDriver);
    }

    @Step("Отменить удаление телефонов")
    public CartPage deleteAllPhoneCancel() {
        emptyCartButton.click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Cancel')]")).click();
        return new CartPage(webDriver);
    }

    @Step("Удалить из корзины телефон {phoneName}")
    public CartPage checkNoItemsCart() {
        assertThat(cartItems.getText())
                .as("Должна быть надпись, что в корзине нет товаров")
                .isEqualTo("No items in the cart.");
        return new CartPage(webDriver);
    }

    @Step("Нажать на кнопку CheckOut")
    public CartPage clickCheckOut() {
        webDriver.findElement(By.xpath(".//button[.='Checkout']")).click();
        return new CartPage(webDriver);
    }

    @Step("Нажать на кнопку Confirm")
    public CartPage clickConfirm() {
        webDriver.findElement(By.xpath(".//button[.='Confirm']")).click();
        return new CartPage(webDriver);
    }

    @Step("Проверить, что покупка совершена успешно")
    public CartPage checkThatCheckOutSuccessful() {
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()=\"Your order has been received. The items you've ordered will be sent to your address.\"]")));
        return new CartPage(webDriver);
    }
}
