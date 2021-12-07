package ru.gb.lesson.lesson6.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import ru.gb.lesson.lesson6.pages.blocks.HeaderBlock;

public class BasePage extends BaseView {

    @Getter
    private HeaderBlock headerBlock = new HeaderBlock(webDriver);

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }
}
