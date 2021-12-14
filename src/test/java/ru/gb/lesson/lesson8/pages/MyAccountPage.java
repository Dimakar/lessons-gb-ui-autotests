package ru.gb.lesson.lesson8.pages;

import io.qameta.allure.Step;
import ru.gb.lesson.lesson8.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class MyAccountPage extends BasePage {

    @Step("Нажать на кнопку Моя персональная информация")
    public MyPersonalInformationPage clickMyPersonalInformationButton() {
        $x("//li[contains(., 'My personal information')]").click();
        return page(MyPersonalInformationPage.class);
    }
}
