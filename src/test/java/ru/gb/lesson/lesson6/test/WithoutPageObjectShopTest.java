package ru.gb.lesson.lesson6.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.gb.lesson.lesson5.BasicTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WithoutPageObjectShopTest extends BasicTest {

    private final String email = "autosupertravel+1117140805004@yandex.ru";
    private final String password = "12345";

    @Test
    @DisplayName("Редактирование профиля: Изменить имя")
    void changeFirstNameTest() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();

        webDriver.findElement(By.xpath("//a[.='My personal information']")).click();

        String oldName = webDriver.findElement(By.id("firstname")).getAttribute("value");
        String newName = oldName + "t";

        webDriver.findElement(By.id("firstname")).clear();
        webDriver.findElement(By.id("firstname")).sendKeys(newName);

        webDriver.findElement(By.name("old_passwd")).sendKeys(password);

        webDriver.findElement(By.name("submitIdentity")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your personal information has been successfully updated.')]")));

        webDriver.findElement(By.xpath("//a[contains(.,'Back to your account')]")).click();

        webDriver.findElement(By.xpath("//a[.='My personal information']")).click();

        assertThat(webDriver.findElement(By.id("firstname")).getAttribute("value"))
                .isEqualTo(newName);
    }


    @Test
    @DisplayName("Редактирование профиля: Изменить фамилию")
    void changeLastNameNameTest() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();

        webDriver.findElement(By.xpath("//a[.='My personal information']")).click();

        String oldName = webDriver.findElement(By.id("lastname")).getAttribute("value");
        String newName = oldName + "t";

        webDriver.findElement(By.id("lastname")).clear();
        webDriver.findElement(By.id("lastname")).sendKeys(newName);

        webDriver.findElement(By.name("old_passwd")).sendKeys(password);

        webDriver.findElement(By.name("submitIdentity")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your personal information has been successfully updated.')]")));

        webDriver.findElement(By.xpath("//a[contains(.,'Back to your account')]")).click();

        webDriver.findElement(By.xpath("//a[.='My personal information']")).click();

        assertThat(webDriver.findElement(By.id("lastname")).getAttribute("value"))
                .isEqualTo(newName);
    }


    @Test
    @DisplayName("Положить в корзину летнюю одежду")
    void selectSummerDressTest() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();


        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//a[.='Women']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//a[.='Summer Dresses']")).click();

        List<WebElement> products = webDriver.findElements(By.className("product-container"));

        WebElement selectedProduct = products.get(0);

        new Actions(webDriver)
                .moveToElement(selectedProduct)
                .build()
                .perform();
        selectedProduct.findElement(By.xpath(".//*[text()='Add to cart']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(., 'Product successfully added to your shopping cart')]")));
    }

    @Test
    @DisplayName("Положить в корзину футболку")
    void selectTShirtTest() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();


        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//a[.='Women']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//a[.='T-shirts']")).click();

        List<WebElement> products = webDriver.findElements(By.className("product-container"));

        WebElement selectedProduct = products.get(0);

        new Actions(webDriver)
                .moveToElement(selectedProduct)
                .build()
                .perform();
        selectedProduct.findElement(By.xpath(".//*[text()='Add to cart']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(., 'Product successfully added to your shopping cart')]")));
    }
}
