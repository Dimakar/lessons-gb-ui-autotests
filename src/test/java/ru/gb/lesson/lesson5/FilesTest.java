package ru.gb.lesson.lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTest extends BasicTest {


    @Test
    void uploadFileTest() {
        webDriver.get("https://demoqa.com/upload-download");
        webDriver.findElement(By.xpath("//input[@type='file']"))
                .sendKeys("/Users/dimakar/GB/lessons-gb-ui-autotests/src/test/resources/logback.xml");

        webDriverWait.until(ExpectedConditions.textToBe(By.id("uploadedFilePath"), "C:\\fakepath\\logback.xml"));
    }
}
