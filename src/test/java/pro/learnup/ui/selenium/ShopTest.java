package pro.learnup.ui.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest extends BaseTest {
    String phoneName = "HTC U11";
    String phoneName2 = "Apple iPhone 8 Plus";
    String phoneName3 = "Apple iPhone X";

    @ParameterizedTest
    @ValueSource(strings={"HTC U11"})
    public void buyPhoneTest(String phoneName) throws InterruptedException {
        login();

        List<WebElement> phones = webDriver.findElements(By.className("product"));
        phones.forEach(p-> System.out.println(p.getText()+"\n\n"));

        phones.stream()
                .filter(el->el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();

        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())
                .as("Должна открыться страница с телефоном" + phoneName)
                .isEqualTo(phoneName);

        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath(".//a[.='CART']")).click();

        List<String> actualPhoneList = webDriver.findElement(By.className("cart-items"))
                .findElements(By.xpath(".//table//tbody/tr"))
                .stream()
                .map(el->el.findElements(By.xpath("./td")).get(1).getText())
                .collect(Collectors.toList());

        assertThat(actualPhoneList).containsExactlyInAnyOrder(phoneName);

        webDriver.findElement(By.xpath(".//button[.='Checkout']")).click();
        webDriverWait.until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Checkout Information']/../table/tbody/tr/td[1]"), phoneName));
        webDriver.findElement(By.xpath(".//button[.='Confirm']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[.='OK']"))).click();
    }
    @Test
    public void filterCPU() throws InterruptedException { //сортировка телефонов по цене и очистка после сортировки
        webDriver.get("http://localhost:3000/");
        login();
        webDriver.findElement(By.xpath("//div[contains(text(),'CPU')]")).click();
        webDriver.findElement(By.xpath("//input[@value='hexa_core']")).click();

        List<WebElement> brandPhones = webDriver.findElements(By.xpath("//div[@class='content-left']"));
        brandPhones.forEach(p-> {
            if (p.getText().contains("Hexa-core")) System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });

        webDriver.findElement(By.xpath("//span[text()='Clear Filters']"));
    }
    @Test
    public void personalData() throws InterruptedException {  //изменение личных данных в личном кабинете
        login();

        webDriver.findElement(By.xpath("//span[text()='ACCOUNT']")).click();
        webDriver.findElement(By.xpath("//button[contains(@aria-label,'Edit')]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Edit Account']"))); // проверяем, что открылось окно для редактирования аккаунта
        webDriver.findElement(By.xpath("//input[contains(@id, 'email')]")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'email')]")).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        webDriver.findElement(By.xpath("//input[contains(@id, 'email')]")).sendKeys("NewEmail");
        webDriver.findElement(By.xpath("//input[contains(@id, 'address')]")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'address')]")).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        webDriver.findElement(By.xpath("//input[contains(@id, 'address')]")).sendKeys("NewAddress");
        webDriver.findElement(By.xpath("//input[contains(@id, 'phone')]")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'phone')]")).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        webDriver.findElement(By.xpath("//input[contains(@id, 'phone')]")).sendKeys("999999");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//span[contains(text(), 'Save')]")).click();
    }
    @Test
    public void DeletePhone() throws InterruptedException {   // добавляем телефоны в корзину и удаляем их разными способами
        login();

        List<WebElement> phones = webDriver.findElements(By.className("product"));
        phones.stream()
                .filter(el->el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())// проверяем, что именно тот телефон выбран
                .as("Должна открыться страница с телефоном" + phoneName)
                .isEqualTo(phoneName);
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Back to catalog')]")).click();

        List<WebElement> phones2 = webDriver.findElements(By.className("product"));
        phones2.stream()
                .filter(el->el.getText().contains(phoneName2))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())// проверяем, что именно тот телефон выбран
                .as("Должна открыться страница с телефоном" + phoneName2)
                .isEqualTo(phoneName2);
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Back to catalog')]")).click();

        List<WebElement> phones3 = webDriver.findElements(By.className("product"));
        phones3.stream()
                .filter(el->el.getText().contains(phoneName3))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())// проверяем, что именно тот телефон выбран
                .as("Должна открыться страница с телефоном" + phoneName3)
                .isEqualTo(phoneName3);
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath(".//a[.='CART']")).click();

        List<String> actualPhoneList = webDriver.findElement(By.className("cart-items"))
                .findElements(By.xpath(".//table//tbody/tr"))
                .stream()
                .map(el->el.findElements(By.xpath("./td")).get(1).getText())
                .collect(Collectors.toList());

        assertThat(actualPhoneList).containsExactlyInAnyOrder(phoneName, phoneName2, phoneName3); //проверяем, что в корзине только выбранные телефоны
        List<WebElement> phones4 = webDriver.findElements(By.className("cart-items"));
        phones4.stream()
                .filter(el->el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath("//button[contains(@title,'Remove this item from the cart')]"))//удалим товар первым способом
                .click();

        webDriver.findElement(By.xpath("//button[.='Empty cart']")).click();//удалим оставшиеся товары вторым способом
        webDriver.findElement(By.xpath("//span[contains(text(), 'Cancel')]")).click();//сперва проверим отмену удаления
        webDriver.findElement(By.xpath("//button[.='Empty cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(text(), 'Yes')]")).click();//теперь удалим окончательно
        Thread.sleep(3000);
        // так как после удаления вторым способом, обязательно должны удалиться ВСЕ телефоны, находящиеся в корзине, проверим, что корзина пуста:
        assertThat(webDriver.findElement(By.className("cart-items")).getText())
                .as("Должна быть надпись, что в корзине нет товаров")
                .isEqualTo("No items in the cart.");
    }
}