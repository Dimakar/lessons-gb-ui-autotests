package ru.gb.lesson.lesson5;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AlertsWindowsFramesTest extends BasicTest{

    @SneakyThrows
    @Test
    void alertAcceptTest() {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("alertButton")).click();
        System.out.println(webDriver.switchTo().alert().getText());
        webDriver.switchTo().alert().accept();
        Thread.sleep(3000);
    }

    @SneakyThrows
    @Test
    void waitAlertAcceptTest() {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("timerAlertButton")).click();
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(webDriver.switchTo().alert().getText());
        webDriver.switchTo().alert().accept();
        Thread.sleep(3000);
    }

    @Test
    void switchToTabTest() {
        webDriver.get("https://demoqa.com/browser-windows");
        webDriver.findElement(By.id("tabButton")).click();
        List<String> windows = List.copyOf(webDriver.getWindowHandles());
        webDriver.switchTo().window(windows.get(1));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='This is a sample page']")));
    }

    @Test
    void switchToFrameTest() {
        webDriver.get("https://demoqa.com/frames");
        webDriver.switchTo().frame(1);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='This is a sample page']")));
    }
}
