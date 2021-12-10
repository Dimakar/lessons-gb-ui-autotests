package ru.gb.lesson.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.lesson.lesson6.listener.AllureWebDriverEventListener;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@ExtendWith(AllureJunit5.class)
public class BasicTest {
    protected EventFiringWebDriver webDriver;
    protected WebDriverWait webDriverWait;
    String url = "https://crm.geekbrains.space/";

    @BeforeEach
    void setUp() {
        webDriver = new EventFiringWebDriver(WebDriverManager.chromedriver().create());
        webDriver.register(new AllureWebDriverEventListener());
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 7);
    }

    @AfterEach
    void tearDown() {
        Iterator<LogEntry> iterator = webDriver.manage().logs().get(LogType.BROWSER).iterator();
        while (iterator.hasNext()) {
            Allure.addAttachment("Log", iterator.next().getMessage());
        }
        webDriver.quit();
    }
}
