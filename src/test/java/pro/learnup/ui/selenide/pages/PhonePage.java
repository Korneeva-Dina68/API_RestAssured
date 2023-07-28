package pro.learnup.ui.selenide.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PhonePage extends BasePage {

    @Step("Проверить, что открылась страница с телефоном {phoneName}")
    public PhonePage checkPhoneName (String phoneName) {
        $(By.cssSelector(".product-details-container h1"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.exactText(phoneName));
        return this;
    }

    @Step("Нажать на кнопку AddToCart")
    public PhonePage clickAddToCart() {
        $x("//button[.='Add to cart']").click();
        return this;
    }

    @Step("Нажать на кнопку Back to catalog")
    public PhonesPage backToCatalog() {
        $x("//span[contains(text(), 'Back to catalog')]").click();
        return page(PhonesPage.class);
    }
}
