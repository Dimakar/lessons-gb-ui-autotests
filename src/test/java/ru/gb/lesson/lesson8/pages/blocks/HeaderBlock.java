package ru.gb.lesson.lesson8.pages.blocks;

import io.qameta.allure.Step;
import ru.gb.lesson.lesson8.pages.ProductPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class HeaderBlock {

    @Step("Перейти на страницу {0} -> {1}")
    public ProductPage goToPage(String firstLevelTab, String secondLevelTab) {
        $x("//a[text()='" + firstLevelTab + "']").hover();
        $x("//a[text()='" + secondLevelTab + "']").click();
        return page(ProductPage.class);
    }
}
