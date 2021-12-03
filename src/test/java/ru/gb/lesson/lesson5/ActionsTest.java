package ru.gb.lesson.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionsTest extends BasicTest {

    @Test
    void dragAndDropToCoordinatesTest() {
        webDriver.get("http://automationpractice.com/index.php?id_category=4&controller=category");
        WebElement sliderPriceFrom = webDriver.findElement(By.xpath("//div[@id='layered_price_slider']/a[1]"));
        WebElement sliderPriceTo = webDriver.findElement(By.xpath("//div[@id='layered_price_slider']/a[2]"));

        WebElement prices = webDriver.findElement(By.id("layered_price_range"));
        assertThat(prices.getText()).isEqualTo("$16.00 - $28.00");

        Actions action = new Actions(webDriver);
        Point pointOfSliderPriceFrom = sliderPriceFrom.getLocation();
        Point pointOfSliderPriceTo = sliderPriceTo.getLocation();
        action
                .dragAndDropBy(sliderPriceFrom, (pointOfSliderPriceTo.x - pointOfSliderPriceFrom.x) / 2, 0)
                .build()
                .perform();

        assertThat(prices.getText()).isEqualTo("$22.00 - $28.00");
    }


    @Test
    void dragAndDropElementToElementTest() {

        webDriver.get("https://demoqa.com/sortable");

        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[@class='list-group-item list-group-item-action']"));

        SoftAssertions asserts1 = new SoftAssertions();
        asserts1.assertThat(elements.get(0).getText()).isEqualTo("One");
        asserts1.assertThat(elements.get(1).getText()).isEqualTo("Two");
        asserts1.assertThat(elements.get(2).getText()).isEqualTo("Three");
        asserts1.assertThat(elements.get(3).getText()).isEqualTo("Four");
        asserts1.assertThat(elements.get(4).getText()).isEqualTo("Five");
        asserts1.assertThat(elements.get(5).getText()).isEqualTo("Six");
        asserts1.assertAll();

        Actions action = new Actions(webDriver);

        action.dragAndDrop(elements.get(0), elements.get(3))
                .build()
                .perform();

        elements = webDriver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[@class='list-group-item list-group-item-action']"));

        SoftAssertions asserts2 = new SoftAssertions();
        asserts1.assertThat(elements.get(0).getText()).isEqualTo("Two");
        asserts1.assertThat(elements.get(1).getText()).isEqualTo("Three");
        asserts1.assertThat(elements.get(2).getText()).isEqualTo("Four");
        asserts1.assertThat(elements.get(3).getText()).isEqualTo("One");
        asserts1.assertThat(elements.get(4).getText()).isEqualTo("Five");
        asserts1.assertThat(elements.get(5).getText()).isEqualTo("Six");
        asserts2.assertAll();
    }
}
