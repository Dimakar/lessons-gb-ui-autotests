package ru.gb.lesson.lesson6.listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static io.qameta.allure.Allure.step;

public class AllureWebDriverEventListener extends AbstractWebDriverEventListener {
    private static final Logger logger = LoggerFactory.getLogger("Action Logger");

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        String message = "Нажать на элемент с текстом " + element.getText();
        logger.info(message);
        step(message);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        String message = "Успешно!";
        logger.info(message);
        step(message);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String message = "Заполнить значением " + Arrays.toString(keysToSend) + " поле " + element.getAttribute("id");
        logger.info(message);
        step(message);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String message = "Успешно!";
        logger.info(message);
        step(message);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Allure.attachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
