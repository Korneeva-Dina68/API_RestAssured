package pro.learnup.ui.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import pro.learnup.ui.selenide.ext.UiTestsExt;
import pro.learnup.ui.selenide.pages.PhonesPage;
import pro.learnup.ui.selenide.pages.blocks.HeaderBlock;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith(UiTestsExt.class)
public class ShopTest extends UiTestsExt{
    String login = "admin10";
    String password = "admin";
    String phoneName = "HTC U11";
    String phoneName2 = "Apple iPhone 8 Plus";
    String phoneName3 = "Apple iPhone X";
    String newAddress = "NewAddress";
    String newEmail = "NewEmail";
    String phoneNumber = "9999999999";
    String brand = "Huawei";
    String internalMemory = "256 GB";
    String cpu = "Hexa-core";
    String ram = "6GB";
    String camera = "16 MP";

    @Feature("Покупка телефона")
    @Description("Покупаем телефон {0}")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Покупка телефона")
    @Test
    public void buyPhoneTest() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .checkPhoneName(phoneName)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainExactly(phoneName)
                .clickCheckOut()
                .clickConfirm()
                .checkThatCheckOutSuccessful();
    }

    @Feature("Фильтр телефонов по ЦП")
    @Description("Фильтр телефонов по ЦП и очистка после сортировки")
    @Severity(SeverityLevel.MINOR)
    @Name("Фильтр телефонов по ЦП")
    @Test
    public void filterCPU() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .clickSearchByCPU()
                .clickSearchByCPUHexaCore()
                .checkFilterPhone(cpu)
                .clickButtonClearFilter()
                .checkClearFilter();
    }

    @Feature("Изменение личных данных")
    @Description("Изменение личных данных в личном кабинете")
    @Severity(SeverityLevel.CRITICAL)
    @Name("Изменение личных данных")
    @Test
    public void personalData() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .getHeaderBlock()
                .clickAccount()
                .checkAccountPage()
                .clickEditAccount()
                .checkEditAccount()
                .clickAndClearAndSendKeysEmailAccount(newEmail)
                .clickAndClearAndSendKeysAddressAccount(newAddress)
                .clickAndClearAndSendKeysPhoneAccount(phoneNumber)
                .clickSaveAccount()
                .checkAccountPage();
    }

    @Description("Добавляем телефоны в корзину и удаляем их разными способами")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Удаление телефонов из корзины")
    @Test
    public void deletePhoneTest() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .checkPhoneName(phoneName)
                .clickAddToCart()
                .backToCatalog()
                .selectPhone(phoneName2)
                .checkPhoneName(phoneName2)
                .clickAddToCart()
                .backToCatalog()
                .selectPhone(phoneName3)
                .checkPhoneName(phoneName3)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainExactly(phoneName, phoneName2, phoneName3)
                .deletePhone(phoneName)
                .deleteAllPhoneCancel()
                .deleteAllPhoneYes()
                .checkNoItemsCart();
    }

    @Description("Фильтруем телефоны по бренду {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по бренду {0}")
    @Test
    public void filterBrandTest() throws InterruptedException {
        open("http://localhost:3000/");
        new HeaderBlock().login(login, password);
        new PhonesPage().clickSearchByBrand();
        new PhonesPage().clickSearchByBrandHuawei();
        new PhonesPage().checkFilterPhone(brand);
        new PhonesPage().clickButtonClearFilter();
        new PhonesPage().checkClearFilter();
    }

    @Description("Фильтруем телефоны по объёму памяти {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по объёму памяти {0}")
    @Test
    public void filterInternalMemoryTest() throws InterruptedException {
        open("http://localhost:3000/");
        new HeaderBlock().login(login, password);
        new PhonesPage().clickSearchByInternalMemory();
        new PhonesPage().clickSearchByInternalMemory256GB();
        new PhonesPage().checkFilterPhone(internalMemory);
        new PhonesPage().clickButtonClearFilter();
        new PhonesPage().checkClearFilter();    }

    @Description("Фильтруем телефоны по объёму оперативной памяти {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по объёму оперативной памяти {0}")
    @Test
    public void filterRAMTest() {
        open("http://localhost:3000/");
        $x("//div[contains(text(),'RAM')]").click();
        $x("//input[@value='6']").click();

        ElementsCollection ramPhones = $$x("//div[@class='content-left']");
        ramPhones.forEach(p-> {
            if (p.getText().contains(ram))
                System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });

        $x("//span[text()='Clear Filters']").click();
        $x("//div[@class='products-found']").shouldBe(Condition.visible)
                .shouldBe(Condition.text("10"));
    }

    @Description("Фильтруем телефоны по количеству мегапикселей {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по количеству мегапикселей {0}")
    @Test
    public void filterCameraTest() {
        open("http://localhost:3000/");
        $x("//div[contains(text(),'Camera')]").click();
        $x("//input[@value='16']").click();

        ElementsCollection cameraPhones = $$x("//div[@class='content-left']");
        cameraPhones.forEach(p-> {
            if (p.getText().contains(camera))
                System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });

        $x("//span[text()='Clear Filters']").click();
        $x("//div[@class='products-found']").shouldBe(Condition.visible)
                .shouldBe(Condition.text("10"));
    }
}