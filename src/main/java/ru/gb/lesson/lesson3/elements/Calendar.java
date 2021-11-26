package ru.gb.lesson.lesson3.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class Calendar implements WrapsElement {
    private WebElement webElement;

    @Override
    public WebElement getWrappedElement() {
        return null;
    }

    public Calendar(WebElement webElement) {
        this.webElement = webElement;
    }

    public Calendar(WebDriver webDriver) {
        this.webElement = webDriver.findElement(By.id("ui-datepicker-div"));
    }

    public void selectDate(LocalDate localDate) {
        new Select(webElement.findElement(By.className("ui-datepicker-month"))).selectByIndex(localDate.getMonthValue() - 1);
        new Select(webElement.findElement(By.className("ui-datepicker-year"))).selectByVisibleText(String.valueOf(localDate.getYear()));
        webElement.findElement(By.xpath(".//td[.='" + localDate.getDayOfMonth() + "']")).click();
    }
}
