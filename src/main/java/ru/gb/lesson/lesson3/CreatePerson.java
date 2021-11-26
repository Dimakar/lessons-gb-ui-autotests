package ru.gb.lesson.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class CreatePerson {
    public static void main(String[] args) throws InterruptedException {
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

        new OrganizationInput(webDriver).selectOrganization("GeekBrains1");

        webDriver.findElement(By.xpath("//input[@placeholder='Укажите дату']")).click();
        new Calendar(webDriver).selectDate(LocalDate.of(1990, 2, 3));
//        webDriver.findElement(By.xpath("//input[@placeholder='Укажите дату']")).click();

        webDriver.findElement(By.xpath("//button[contains(text(), 'Сохранить') and not(contains(text(), 'закрыть'))]")).click();

        new WebDriverWait(webDriver, 10, 500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Контактное лицо сохранено']")));

        Thread.sleep(10000);
        System.out.println(webDriver.findElement(By.xpath("//label[text()='Фамилия']/following-sibling::div")).getText());
        webDriver.quit();
    }
}
