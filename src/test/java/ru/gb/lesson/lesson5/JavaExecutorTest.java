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
        webDriver.findElement(By.id("showSmallModal")).click();
        WebElement modalWindow = webDriver.findElement(By.xpath("//div[@class='modal-dialog modal-sm']"));

        Thread.sleep(3000);

        js.executeScript("arguments[0].remove()", modalWindow);

        Thread.sleep(10000);
    }

}
