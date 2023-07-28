package pro.learnup.ui.pageobject;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import pro.learnup.ui.pageobject.pages.PhonesPage;
import pro.learnup.ui.pageobject.pages.blocks.HeaderBlock;

import static io.qameta.allure.Allure.parameter;


public class ShopTest extends BaseTest {
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

    @Feature("Покупка телефона")
    @Description("Покупаем телефон {0}")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Покупка телефона")
    @Test
    public void buyPhoneTest() throws InterruptedException {
        webDriver.get("http://localhost:3000/");

        new PhonesPage(webDriver)
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

    @Feature("Cортировка телефонов")
    @Description("Cортировка телефонов по цене и очистка после сортировки")
    @Severity(SeverityLevel.MINOR)
    @Name("Cортировка телефонов")
    @Test
    public void filterCPU() throws InterruptedException {
        webDriver.get("http://localhost:3000/");

        new PhonesPage(webDriver)
                .getHeaderBlock()
                .login(login, password)
                .clickSearchByCPU()
                .clickSearchByCPUHexaCore()
                .checkFilterPhone(cpu)
                .clickButtonClearFilter();
    }

    @Feature("Изменение личных данных")
    @Description("Изменение личных данных в личном кабинете")
    @Severity(SeverityLevel.CRITICAL)
    @Name("Изменение личных данных")
    @Test
    public void personalData() throws InterruptedException {
        webDriver.get("http://localhost:3000/");

        new PhonesPage(webDriver)
                .getHeaderBlock()
                .login(login, password)
                .getHeaderBlock()
                .clickAccount()
                .clickEditAccount()
                .checkEditAccount()
                .clickAndClearAndSendKeysEmailAccount(newEmail)
                .clickAndClearAndSendKeysAddressAccount(newAddress)
                .clickAndClearAndSendKeysPhoneAccount(phoneNumber)
                .clickSaveAccount();
    }

    @Description("Добавляем телефоны в корзину и удаляем их разными способами")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Удаление телефонов из корзины")
    @Test
    public void deletePhoneTest() throws InterruptedException {
        webDriver.get("http://localhost:3000/");

        new PhonesPage(webDriver)
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
    public void filterBrandTest() {
        webDriver.get("http://localhost:3000/");
        new HeaderBlock(webDriver).login(login, password);
        new PhonesPage(webDriver).clickSearchByBrand();
        new PhonesPage(webDriver).clickSearchByBrandHuawei();
        new PhonesPage(webDriver).checkFilterPhone(brand);
        new PhonesPage(webDriver).clickButtonClearFilter();
    }

    @Description("Фильтруем телефоны по объёму памяти {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по объёму памяти {0}")
    @Test
    public void filterInternalMemoryTest() {
        webDriver.get("http://localhost:3000/");
        new HeaderBlock(webDriver).login(login, password);
        new PhonesPage(webDriver).clickSearchByInternalMemory();
        new PhonesPage(webDriver).clickSearchByInternalMemory256GB();
        new PhonesPage(webDriver).checkFilterPhone(internalMemory);
        new PhonesPage(webDriver).clickButtonClearFilter();
    }
}