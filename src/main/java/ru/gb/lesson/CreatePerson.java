package ru.gb.lesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreatePerson {
    public static void main(String[] args) {
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.get("https://crm.geekbrains.space/user/login");
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

        webDriver.findElement(By.name("_username")).sendKeys("Applanatest1");
        webDriver.findElement(By.name("_password")).sendKeys("Student2020!");
        webDriver.findElement(By.xpath("//button[text()='Войти']")).click();


        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//span[text()='Контрагенты']"))).build()
                .perform();
        webDriver.findElement(By.xpath("//span[text()='Контактные лица']")).click();


        new WebDriverWait(webDriver, 10, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Создать контактное лицо']")))
                .click();

        webDriver.findElement(By.name("crm_contact[lastName]")).sendKeys("Павликов");
        webDriver.findElement(By.name("crm_contact[firstName]")).sendKeys("Павлик");
        webDriver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Менеджер");

        webDriver.findElement(By.xpath("//label[text()='Организация']/../following-sibling::div//a")).click();

        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]"))));
        webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]/../preceding-sibling::div/input"))
                .sendKeys("GeekBrains1");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(.,'GeekBrains1')]"))).click();

        webDriver.findElement(By.xpath("//button[contains(text(), 'Сохранить') and not(contains(text(), 'закрыть'))]")).click();

        new WebDriverWait(webDriver, 10, 500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Контактное лицо сохранено']")));

        System.out.println(webDriver.findElement(By.xpath("//label[text()='Фамилия']/following-sibling::div")).getText());
        webDriver.quit();
    }
}
