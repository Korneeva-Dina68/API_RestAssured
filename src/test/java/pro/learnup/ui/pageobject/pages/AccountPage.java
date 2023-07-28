package pro.learnup.ui.pageobject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage{
    @FindBy(xpath = "//input[contains(@id, 'email')]")
    private WebElement emailAccount;
    @FindBy(xpath = "//input[contains(@id, 'address')]")
    private WebElement addressAccount;
    @FindBy(xpath = "//input[contains(@id, 'phone')]")
    private WebElement phoneAccount;
    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать кнопку редактирования аккаунта")
    public AccountPage clickEditAccount() {
        webDriver.findElement(By.xpath("//button[contains(@aria-label,'Edit')]")).click();
        return new AccountPage(webDriver);
    }

    @Step("Проверить, что мы на странице редактирования аккаунта")
    public AccountPage checkEditAccount() {
        new WebDriverWait(webDriver,3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Edit Account']")));
        return new AccountPage(webDriver);
    }

    @Step("Очистить и ввести данные {newEmail} в строку ввода Email")
    public AccountPage clickAndClearAndSendKeysEmailAccount(String newEmail) {
        emailAccount.click();
        emailAccount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        emailAccount.sendKeys(newEmail);
        return new AccountPage(webDriver);
    }

    @Step("Очистить и ввести данные {newAddress} в строку ввода Address")
    public AccountPage clickAndClearAndSendKeysAddressAccount(String newAddress) {
        addressAccount.click();
        addressAccount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        addressAccount.sendKeys(newAddress);
        return new AccountPage(webDriver);
    }

    @Step("Очистить и ввести данные {phoneNumber} в строку ввода Telephone Number")
    public AccountPage clickAndClearAndSendKeysPhoneAccount(String phoneNumber) {
        phoneAccount.click();
        phoneAccount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        phoneAccount.sendKeys(phoneNumber);
        return new AccountPage(webDriver);
    }

    @Step("Нажать кнопку Save в форме редактирования данных")
    public AccountPage clickSaveAccount() {
        webDriver.findElement(By.xpath("//span[contains(text(), 'Save')]")).click();
        return new AccountPage(webDriver);
    }
}
