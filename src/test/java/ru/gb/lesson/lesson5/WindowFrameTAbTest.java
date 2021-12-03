package ru.gb.lesson.lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WindowFrameTAbTest extends BasicTest {

    @Test
    void switchToTabTest() {
        webDriver.get("https://demoqa.com/browser-windows");

        webDriver.findElement(By.id("tabButton")).click();
        List<String> tabs = List.copyOf(webDriver.getWindowHandles());

        assertThat(tabs).as("Должно быть открыто 2 вкладки").hasSize(2);

        webDriver.switchTo().window(tabs.get(1));

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'This is a sample page')]")));
    }

    @Test
    void switchToFrameTest() {
        webDriver.get("https://demoqa.com/frames");
        webDriver.switchTo().frame(0);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'This is a sample page')]")));
        webDriver.switchTo().frame(1);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'This is a sample page')]")));
    }

    @Test
    void alertAcceptTest() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("alertButton")).click();
        webDriver.switchTo().alert().accept();
        Thread.sleep(1000);
    }

    @Test
    void alertDismissTest() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("confirmButton")).click();
        webDriver.switchTo().alert().dismiss();
        Thread.sleep(1000);
    }

    @Test
    void alertSendKeyTest() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("promtButton")).click();
        webDriver.switchTo().alert().sendKeys("sfdf");
        Thread.sleep(1000);
    }
}
