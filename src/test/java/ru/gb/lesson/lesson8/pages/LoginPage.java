package ru.gb.lesson.lesson8.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.gb.lesson.lesson8.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {

    private SelenideElement emailInput = $(By.id("email"));
    private SelenideElement passwordInput = $(By.id("passwd"));
    private SelenideElement submitButton = $(By.id("SubmitLogin"));

    @Step("Авторизоваться юзером {0} с паролем {1}")
    public MyAccountPage login(String username, String password) {
        emailInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        return page(MyAccountPage.class);
    }
}
