package ru.gb.lesson.lesson6.pages.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.gb.lesson.lesson6.pages.BaseView;
import ru.gb.lesson.lesson6.pages.ProductPage;

public class HeaderBlock extends BaseView {

    public HeaderBlock(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductPage goToPage(String firstLevelTab, String secondLevelTab) {
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//a[text()='" + firstLevelTab + "']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//a[text()='" + secondLevelTab + "']")).click();
        return new ProductPage(webDriver);
    }
}
