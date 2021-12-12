package ru.gb.lesson.lesson8.extention;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConfigExtension implements AfterEachCallback, BeforeAllCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Selenide.closeWebDriver();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
}
