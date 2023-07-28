package pro.learnup.ui.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DragAndDropTest extends BaseTest{
    @Test
    void dragAndDropTest() {
        webDriver.get("https://demoqa.com/sortable");
        List<WebElement> blocks = webDriver.findElements(By.xpath("//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action']"));
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Six");
        softAssertions.assertAll();

        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("One");
        softAssertions.assertAll();

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Two");
        softAssertions.assertAll();

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Three");
        softAssertions.assertAll();

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Four");
        softAssertions.assertAll();

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Five");
        softAssertions.assertAll();

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Six");
        softAssertions.assertAll();
    }
}
