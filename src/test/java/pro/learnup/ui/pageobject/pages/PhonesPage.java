package pro.learnup.ui.pageobject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PhonesPage extends BasePage {
    public PhonesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Выбрать телефон {phoneName}")
    public PhonePage selectPhone(String phoneName) {
        List<WebElement> phones = webDriver.findElements(By.className("product"));
        phones.stream()
                .filter(el -> el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();
        return new PhonePage(webDriver);
    }

    @Step("Нажать кнопку Price")
    public PhonesPage clickSearchByPrice() {
        webDriver.findElement(By.xpath(" //*[contains(text(),'Price')]")).click();
        return new PhonesPage(webDriver);
    }

    @Step("Нажать кнопку >750")
    public PhonesPage clickSearchByPriceMore750(){
        webDriver.findElement(By.xpath(" //input[contains(@value,'750>')]")).click();
        return new PhonesPage(webDriver);
    }

    @Step("Нажать кнопку Brand")
    public void clickSearchByBrand() {
        webDriver.findElement(By.xpath("//div[contains(text(),'Brand')]")).click();
    }

    @Step("Нажать кнопку Huawei")
    public void clickSearchByBrandHuawei() {
        webDriver.findElement(By.xpath("//input[@value='huawei']")).click();
    }

    @Step("Нажать кнопку Internal memory")
    public void clickSearchByInternalMemory() {
        webDriver.findElement(By.xpath("//div[contains(text(),'Internal memory')]")).click();
    }

    @Step("Нажать кнопку 256GB")
    public void clickSearchByInternalMemory256GB() {
        webDriver.findElement(By.xpath("//input[@value='256']")).click();
    }

    @Step("Нажать кнопку CPU")
    public PhonesPage clickSearchByCPU() {
        webDriver.findElement(By.xpath("//div[contains(text(),'CPU')]")).click();
        return new PhonesPage(webDriver);
    }

    @Step("Нажать кнопку Hexa_core")
    public PhonesPage clickSearchByCPUHexaCore() {
        webDriver.findElement(By.xpath("//input[@value='hexa_core']")).click();
        return new PhonesPage(webDriver);
    }

    @Step("Проверяем, что после фильтра телефоны только {filter}")
    public PhonesPage checkFilterPhone(String filter) {
        List<WebElement> brandPhones = webDriver.findElements(By.xpath("//div[@class='content-left']"));
        brandPhones.forEach(p-> {
            if (p.getText().contains(filter)) System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });
        return PhonesPage.this;
    }

    @Step("Нажать кнопку Clear Filters")
    public PhonesPage clickButtonClearFilter() {
        webDriver.findElement(By.xpath("//span[text()='Clear Filters']")).click();
        return new PhonesPage(webDriver);
    }
}
