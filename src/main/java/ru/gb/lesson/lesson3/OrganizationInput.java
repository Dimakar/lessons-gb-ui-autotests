package ru.gb.lesson.lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationInput implements WrapsElement {
    private WebElement wrappedElement;
    private WebDriver webDriver;

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public OrganizationInput(WebDriver webDriver) {
        this.wrappedElement = webDriver.findElement(By.xpath("//label[text()='Организация']/../following-sibling::div//a"));
        this.webDriver = webDriver;
    }

    public void selectOrganization(String organizationName) {
        webDriver.findElement(By.xpath("//label[text()='Организация']/../following-sibling::div//a")).click();

        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]"))));
        webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]/../preceding-sibling::div/input"))
                .sendKeys("GeekBrains1");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(.,'GeekBrains1')]"))).click();
    }

}
