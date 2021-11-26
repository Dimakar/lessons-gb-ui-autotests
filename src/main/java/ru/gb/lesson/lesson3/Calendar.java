package ru.gb.lesson.lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class Calendar implements WrapsElement {
    private WebElement wrappedElement;

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public Calendar(WebDriver webDriver) {
        this.wrappedElement = webDriver.findElement(By.id("ui-datepicker-div"));
    }

    public void selectDate(LocalDate date) {
        new Select(wrappedElement.findElement(By.xpath(".//select[@class='ui-datepicker-month']"))).selectByIndex(date.getMonthValue() - 1);
        new Select(wrappedElement.findElement(By.xpath(".//select[@class='ui-datepicker-year']"))).selectByVisibleText(String.valueOf(date.getYear()));
        wrappedElement.findElement(By.xpath(".//td[.='" + date.getDayOfMonth() + "']")).click();
    }
}
