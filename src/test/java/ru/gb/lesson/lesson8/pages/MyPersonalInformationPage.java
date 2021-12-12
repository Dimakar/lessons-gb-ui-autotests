package ru.gb.lesson.lesson8.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MyPersonalInformationPage extends BasePage {

    private SelenideElement firstName = $(By.id("firstname"));
    private SelenideElement lastname = $(By.id("lastname"));
    private SelenideElement password = $(By.id("old_passwd"));
    private SelenideElement saveButton = $(By.xpath("//button[contains(., 'Save')]"));
    private SelenideElement backToAccountButton = $(By.xpath("//li[contains(., 'Back to your account')]"));

    @Step("Изменить имя на {0}")
    public MyPersonalInformationPage changeFirstName(String name) {
        firstName.clear();
        firstName.sendKeys(name);
        return this;
    }

    @Step("Изменить фамилию на {0}")
    public MyPersonalInformationPage changeLastName(String name) {
        lastname.clear();
        lastname.sendKeys(name);
        return this;
    }

    @Step("Ввести пароль")
    public MyPersonalInformationPage inputPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку Сохранить")
    public MyPersonalInformationPage clickSaveButton() {
        $(By.xpath("//button[contains(., 'Save')]")).click();
        return this;
    }

    @Step("Нажать на кнопку Вернуться в свой аккаунт")
    public MyAccountPage clickBackToYourAccountButton() {
        $(By.xpath("//li[contains(., 'Back to your account')]")).click();
        return page(MyAccountPage.class);
    }

    @Step("Проверить, что имя равно {0}")
    public MyPersonalInformationPage checkFirstName(String name) {
        assertThat(firstName.getAttribute("value"))
                .isEqualTo(name);
        return this;
    }

    @Step("Проверить, что фамилия равна {0}")
    public MyPersonalInformationPage checkLastName(String name) {
        assertThat(lastname.getAttribute("value"))
                .isEqualTo(name);
        return this;
    }


}
