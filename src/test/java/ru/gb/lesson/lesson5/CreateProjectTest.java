package ru.gb.lesson.lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.lesson.lesson3.elements.OrganizationDropDownList;

import java.time.Duration;
import java.util.UUID;

public class CreateProjectTest extends BasicTest {

    @Test
    void createProjectTest() {
        webDriver.get(url);

        webDriver.manage().window().setSize(new Dimension(1500, 720));

        webDriver.findElement(By.name("_username")).sendKeys("Applanatest1");
        webDriver.findElement(By.name("_password")).sendKeys("Student2020!");
        webDriver.findElement(By.name("_submit")).click();

        Actions actions = new Actions(webDriver);

        actions.moveToElement(webDriver.findElement(By.xpath("//a[./span[text()='Проекты']]")))
                .perform();

        webDriver.findElement(By.xpath("//a[./span[text()='Мои проекты']]")).click();

        new WebDriverWait(webDriver, 10, 500)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Создать проект']")))
                .click();

        webDriver.findElement(By.name("crm_project[name]")).sendKeys("Орг" + UUID.randomUUID());

        new Select(webDriver.findElement(By.xpath("//label[text()='Приоритет']/../following-sibling::div//select"))).selectByVisibleText("Низкий");
        new Select(webDriver.findElement(By.xpath("//label[text()='Финансирование']/../following-sibling::div//select"))).selectByVisibleText("Внутреннее");
        new Select(webDriver.findElement(By.xpath("//label[text()='Подразделение']/../following-sibling::div//select"))).selectByVisibleText("Research & Development");
        new Select(webDriver.findElement(By.xpath("//label[text()='Куратор проекта']/../following-sibling::div//select"))).selectByVisibleText("Коблев Евгений");
        new Select(webDriver.findElement(By.xpath("//label[text()='Руководитель проекта']/../following-sibling::div//select"))).selectByVisibleText("Марков Глеб");
        new Select(webDriver.findElement(By.xpath("//label[text()='Менеджер']/../following-sibling::div//select"))).selectByVisibleText("Козлов Илья");

        new OrganizationDropDownList(webDriver).selectOrganization("GeekBrains1");

        webDriver.findElement(By.xpath("//button[contains(text(),'Сохранить') and not(contains(text(), 'закрыть'))]")).click();
        new WebDriverWait(webDriver, 8).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Проект сохранен')]")));
    }
}
