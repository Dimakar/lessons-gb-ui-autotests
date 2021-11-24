package ru.gb.lesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.get("https://crm.geekbrains.space/user/login");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

//        webDriver.manage().getCookies().clear();
//        webDriver.manage().addCookie(new Cookie("BAPID", "92f578bd571cbc6d5b4a97a30b338600"));
//        webDriver.get("https://crm.geekbrains.space");

        By loginInput = By.name("_username");
        By passwordInput = By.name("_password");

        webDriver.findElement(loginInput).sendKeys("**");
        webDriver.findElement(passwordInput).sendKeys("**");
        webDriver.findElement(By.xpath("//button[text()='Войти']")).click();


        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//span[text()='Проекты']"))).build()
                        .perform();
        webDriver.findElement(By.xpath("//span[text()='Все проекты']")).click();


        new WebDriverWait(webDriver, 10, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//a[text()='Создать проект']"))))
                .click();

        webDriver.findElement(By.xpath("//input[@name='crm_project[name]']")).sendKeys("Организация" + UUID.randomUUID());

        new Select(webDriver.findElement(By.xpath("//label[text()='Приоритет']/../following-sibling::div//select"))).selectByVisibleText("Низкий");
        new Select(webDriver.findElement(By.xpath("//label[text()='Финансирование']/../following-sibling::div//select"))).selectByVisibleText("Внутреннее");
        new Select(webDriver.findElement(By.xpath("//label[text()='Подразделение']/../following-sibling::div//select"))).selectByVisibleText("Research & Development");
        new Select(webDriver.findElement(By.xpath("//label[text()='Куратор проекта']/../following-sibling::div//select"))).selectByVisibleText("Ким Юрий");
        new Select(webDriver.findElement(By.xpath("//label[text()='Руководитель проекта']/../following-sibling::div//select"))).selectByVisibleText("Ермакова Анастасия");
        new Select(webDriver.findElement(By.xpath("//label[text()='Менеджер']/../following-sibling::div//select"))).selectByVisibleText("Горячев Алексей");


        Thread.sleep(15000);
        webDriver.quit();
    }
}
