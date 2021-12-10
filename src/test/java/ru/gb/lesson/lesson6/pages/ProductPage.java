package ru.gb.lesson.lesson6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Выбрать продукт номер {0}")
    public ProductPage selectProduct(int number) {
        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='product-container']"));

        new Actions(webDriver).moveToElement(products.get(number))
                .build()
                .perform();
        products.get(number).findElement(By.xpath(".//*[text()='Add to cart']")).click();
        return this;
    }

    @Step("Проверить, что продукт добавлен в корзину")
    public ProductPage checkProductAdded() {
        new WebDriverWait(webDriver, 8).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//h2[contains(., 'Product successfully added to your shopping cart')]")));
        return this;
    }
}
