package ru.gb.lesson.lesson6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на Мою персональную информацию")
    public MyPersonalInformationPage clickMyPersonalInformationButton() {
        webDriver.findElement(By.xpath("//li[contains(., 'My personal information')]")).click();
        return new MyPersonalInformationPage(webDriver);
    }
}
