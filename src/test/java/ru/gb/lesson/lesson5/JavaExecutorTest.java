package ru.gb.lesson.lesson5;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaExecutorTest extends BasicTest {

    @SneakyThrows
    @Test
    void deleteModalWindowTest() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        webDriver.get("https://demoqa.com/modal-dialogs");
        WebElement button = webDriver.findElement(By.id("showSmallModal"));
        js.executeScript("arguments[0].click()", button);
        WebElement modalWindow = webDriver.findElement(By.xpath("//div[contains(@class,'modal-dialog')]"));
        js.executeScript("arguments[0].remove()", modalWindow);
        Thread.sleep(3000);
    }
}
