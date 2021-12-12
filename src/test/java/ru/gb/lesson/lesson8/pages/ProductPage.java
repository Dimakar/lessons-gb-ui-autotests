package ru.gb.lesson.lesson8.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    @Step("Выбрать продукт номер {0}")
    public ProductPage selectProduct(int number) {
        ElementsCollection products = $$(By.xpath("//div[@class='product-container']"));
        products.get(0).hover()
                        .$x(".//*[text()='Add to cart']").click();
        return this;
    }

    @Step("Проверить, что продукт добавлен в корзину")
    public ProductPage checkProductAdded() {
        $x("//h2[contains(., 'Product successfully added to your shopping cart')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(8));
        return this;
    }
}
