package pro.learnup.ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class PhonesPage extends BasePage {

    @Step("Выбрать телефон {phoneName}")
    public PhonePage selectPhone(String phoneName) {
        ElementsCollection phones = $$(By.className("product"));
        phones.stream()
                .filter(el -> el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();
        return page(PhonePage.class);
    }

    @Step("Нажать категорию Brand")
    public void clickSearchByBrand() {
        $x("//div[contains(text(),'Brand')]").click();
    }

    @Step("Нажать подкатегорию Huawei")
    public void clickSearchByBrandHuawei() {
        $x("//input[@value='huawei']").click();
    }

    @Step("Нажать категорию Internal memory")
    public void clickSearchByInternalMemory() {
        $x("//div[contains(text(),'Internal memory')]").click();
    }

    @Step("Нажать подкатегорию 256GB")
    public void clickSearchByInternalMemory256GB() {
        $x("//input[@value='256']").click();
    }

    @Step("Нажать категорию CPU")
    public PhonesPage clickSearchByCPU() {
        $x("//div[contains(text(),'CPU')]").click();
        return page(PhonesPage.class);
    }

    @Step("Нажать подкатегорию Hexa_core")
    public PhonesPage clickSearchByCPUHexaCore() {
        $x("//input[@value='hexa_core']").click();
        return page(PhonesPage.class);
    }

    @Step("Проверяем, что после фильтра телефоны только {filter}")
    public PhonesPage checkFilterPhone(String filter) {
        ElementsCollection brandPhones = $$x("//div[@class='content-left']");
        brandPhones.forEach(p-> {
            if (p.getText().contains(filter)) System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });
        return this;
    }

    @Step("Нажать кнопку Clear Filters")
    public PhonesPage clickButtonClearFilter() {
        $x("//button[.='Clear Filters']").click();
        return page(PhonesPage.class);
    }

    @Step("Проверяем, что фильтр сброшен")
    public PhonesPage checkClearFilter() {
        $x("//div[@class='products-found']").shouldBe(Condition.visible)
                .shouldBe(Condition.text("10"));
        return page(PhonesPage.class);
    }
}
