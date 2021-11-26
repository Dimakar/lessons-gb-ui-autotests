package ru.gb.lesson.lesson3.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationDropDownList implements WrapsElement {
    private WebElement webElement;
    private WebDriver webDriver;

    public OrganizationDropDownList(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webElement = webDriver.findElement(By.xpath("//span[text()='Укажите организацию']/.."));
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }

    public void selectOrganization(String orgName) {
        webElement.click();
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(., 'Все организации')]")));
        webDriver.findElement(By.xpath("//li[contains(., 'Все организации')]/../preceding-sibling::div/input")).sendKeys(orgName);
        webDriver.findElement(By.xpath("//li[contains(., '" + orgName + "')]")).click();
    }
}
