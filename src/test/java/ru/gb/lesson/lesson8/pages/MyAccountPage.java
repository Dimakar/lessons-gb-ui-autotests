package ru.gb.lesson.lesson8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MyAccountPage extends BasePage {

    @Step("Нажать на кнопку Моя персональная информация")
    public MyPersonalInformationPage clickMyPersonalInformationButton() {
        $(By.xpath("//li[contains(., 'My personal information')]")).click();
        return page(MyPersonalInformationPage.class);
    }
}
