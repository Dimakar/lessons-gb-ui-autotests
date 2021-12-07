package ru.gb.lesson.lesson6;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.gb.lesson.lesson5.BasicTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopWithoutPageObjectTest extends BasicTest {
    private final String username = "autosupertravel+1117140805004@yandex.ru";
    private final String password = "12345";
    private final Faker faker = new Faker();

    @Test
    void changeFirstNameTest() {
        String name = faker.name().firstName();

        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        webDriver.findElement(By.id("email")).sendKeys(username);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();
        webDriver.findElement(By.xpath("//li[contains(., 'My personal information')]")).click();

        webDriver.findElement(By.id("firstname")).clear();
        webDriver.findElement(By.id("firstname")).sendKeys(name);

        webDriver.findElement(By.id("old_passwd")).sendKeys(password);
        webDriver.findElement(By.xpath("//button[contains(., 'Save')]")).click();

        webDriver.findElement(By.xpath("//li[contains(., 'Back to your account')]")).click();

        webDriver.findElement(By.xpath("//li[contains(., 'My personal information')]")).click();

        assertThat(webDriver.findElement(By.id("firstname")).getAttribute("value"))
                .isEqualTo(name);
    }


    @Test
    void changeLastNameTest() {
        String name = faker.name().lastName();

        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        webDriver.findElement(By.id("email")).sendKeys(username);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();
        webDriver.findElement(By.xpath("//li[contains(., 'My personal information')]")).click();

        webDriver.findElement(By.id("lastname")).clear();
        webDriver.findElement(By.id("lastname")).sendKeys(name);

        webDriver.findElement(By.id("old_passwd")).sendKeys(password);
        webDriver.findElement(By.xpath("//button[contains(., 'Save')]")).click();

        webDriver.findElement(By.xpath("//li[contains(., 'Back to your account')]")).click();

        webDriver.findElement(By.xpath("//li[contains(., 'My personal information')]")).click();

        assertThat(webDriver.findElement(By.id("lastname")).getAttribute("value"))
                .isEqualTo(name);
    }

    @Test
    void selectSummerDressTest() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        webDriver.findElement(By.id("email")).sendKeys(username);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();


        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//a[text()='Women']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//a[text()='Summer Dresses']")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='product-container']"));

        new Actions(webDriver).moveToElement(products.get(0))
                .build()
                .perform();
        products.get(0).findElement(By.xpath(".//*[text()='Add to cart']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//h2[contains(., 'Product successfully added to your shopping cart')]")));
    }

    @Test
    void selectTShirtsTest() {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        webDriver.findElement(By.id("email")).sendKeys(username);
        webDriver.findElement(By.id("passwd")).sendKeys(password);
        webDriver.findElement(By.id("SubmitLogin")).click();


        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//a[text()='Women']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//a[text()='T-shirts']")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='product-container']"));

        new Actions(webDriver).moveToElement(products.get(0))
                .build()
                .perform();
        products.get(0).findElement(By.xpath(".//*[text()='Add to cart']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//h2[contains(., 'Product successfully added to your shopping cart')]")));
    }
}
