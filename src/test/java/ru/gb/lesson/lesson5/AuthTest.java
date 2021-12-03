package ru.gb.lesson.lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthTest extends BasicTest {

    @Test
    void authUiTest() {
        webDriver.get("http://the-internet.herokuapp.com/login");
        webDriver.findElement(By.name("username")).sendKeys("tomsmith");
        webDriver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        webDriver.findElement(By.xpath("//button[contains(.,'Login')]")).click();

        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//h2[contains(., 'Secure Area')]")));
    }

    @Test
    void authCookieTest() {
        webDriver.get("http://the-internet.herokuapp.com/login");
        webDriver.manage().getCookies().clear();
        //куки получаем дернув запрос на авторизацию по http или используем постоянный, если на тестовом стенде есть такая возможность
        webDriver.manage().addCookie(new Cookie("rack.session", "BAh7CkkiD3Nlc3Npb25faWQGOgZFVEkiRWUyYmI5NDNiOWMyZTYzNTY5YzI0%0AZTUyNjcyNTc5YWE3M2I0MjlkODhmZTA5M2YxNjZkNzFlN2ZjMTI3MTFlODYG%0AOwBGSSIJY3NyZgY7AEZJIiVjMzY1YWExYjNhZGUzMzI0ZTQzMWFiODdhZmNk%0AMjhmMgY7AEZJIg10cmFja2luZwY7AEZ7B0kiFEhUVFBfVVNFUl9BR0VOVAY7%0AAFRJIi1jNjk3MTM0YWMxNTg5Y2ZmNzFmNjA0YTc1ODhmYjFhMmIxMDkxMjYz%0ABjsARkkiGUhUVFBfQUNDRVBUX0xBTkdVQUdFBjsAVEkiLWM2OWVjOTEzYTg1%0AY2UyMmNjNmM4NjJmYWRlZjdmNWFhMmM2M2JmODkGOwBGSSIKZmxhc2gGOwBG%0AewBJIg11c2VybmFtZQY7AEZJIg10b21zbWl0aAY7AFQ%3D%0A--47a7a6bc7f017fab70aca94ca0324102c5194210"));
        webDriver.get("http://the-internet.herokuapp.com/secure");
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//h2[contains(., 'Secure Area')]")));
    }

    @Test
    void authBasicTest() {
        webDriver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Congratulations! You must have the proper credentials.')]")));
    }
}