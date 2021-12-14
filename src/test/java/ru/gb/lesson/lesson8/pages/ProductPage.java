package ru.gb.lesson.lesson8.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.gb.lesson.lesson8.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    @Step("Выбрать продукт номер {0}")
    public ProductPage selectProduct(int number) {
        ElementsCollection products = $$x("//div[@class='product-container']");
        products.get(number).hover()
                .$(By.xpath(".//*[text()='Add to cart']")).click();
        return this;
    }

    @Step("Проверить, что продукт добавлен в корзину")
    public ProductPage checkProductAdded() {
        $x("//h2[contains(., 'Product successfully added to your shopping cart')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(8));
        return this;
    }
}
