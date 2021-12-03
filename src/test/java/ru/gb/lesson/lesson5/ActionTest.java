package ru.gb.lesson.lesson5;

import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionTest extends BasicTest {

    @Test
    void dragAndDropTest() {
        webDriver.get("https://demoqa.com/sortable");

        List<WebElement> elements = webDriver.findElement(By.id("demo-tabpane-list"))
                .findElements(By.xpath(".//div[contains(@class,'list-group-item')]"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(elements.get(0).getText()).isEqualTo("One");
        softAssertions.assertThat(elements.get(1).getText()).isEqualTo("Two");
        softAssertions.assertThat(elements.get(2).getText()).isEqualTo("Three");
        softAssertions.assertThat(elements.get(3).getText()).isEqualTo("Four");
        softAssertions.assertThat(elements.get(4).getText()).isEqualTo("Five");
        softAssertions.assertThat(elements.get(5).getText()).isEqualTo("Six");
        softAssertions.assertAll();


        Actions action = new Actions(webDriver);

        action.moveToElement(elements.get(0))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .moveToElement(elements.get(5))
                .release()
                .build()
                .perform();

        softAssertions.assertThat(elements.get(0).getText()).isEqualTo("Two");
        softAssertions.assertThat(elements.get(1).getText()).isEqualTo("Three");
        softAssertions.assertThat(elements.get(2).getText()).isEqualTo("Four");
        softAssertions.assertThat(elements.get(3).getText()).isEqualTo("Five");
        softAssertions.assertThat(elements.get(4).getText()).isEqualTo("Six");
        softAssertions.assertThat(elements.get(5).getText()).isEqualTo("One");
        softAssertions.assertAll();


        action.dragAndDrop(elements.get(0), elements.get(5))
                .build()
                .perform();

        softAssertions.assertThat(elements.get(0).getText()).isEqualTo("Three");
        softAssertions.assertThat(elements.get(1).getText()).isEqualTo("Four");
        softAssertions.assertThat(elements.get(2).getText()).isEqualTo("Five");
        softAssertions.assertThat(elements.get(3).getText()).isEqualTo("Six");
        softAssertions.assertThat(elements.get(4).getText()).isEqualTo("One");
        softAssertions.assertThat(elements.get(5).getText()).isEqualTo("Two");
        softAssertions.assertAll();
    }


    @SneakyThrows
    @Test
    void dragAndDropPriceFilterTest() {
        webDriver.get("http://automationpractice.com/index.php?id_category=5&controller=category");

        assertThat(webDriver.findElement(By.id("layered_price_range")).getText())
                .isEqualTo("$16.00 - $17.00");

        WebElement slider = webDriver.findElement(By.id("layered_price_slider"));

        List<WebElement> cursorsPrices = slider.findElements(By.xpath("./a"));
        assertThat(cursorsPrices).hasSize(2);

        Point pointFrom = cursorsPrices.get(0).getLocation();
        Point pointTo = cursorsPrices.get(1).getLocation();

        Actions actions = new Actions(webDriver);

        actions.dragAndDropBy(cursorsPrices.get(0), (pointTo.x - pointFrom.x) / 2, 0)
                .build()
                .perform();

        Thread.sleep(5000);

        assertThat(webDriver.findElement(By.id("layered_price_range")).getText())
                .isEqualTo("$16.50 - $17.00");

    }
}
