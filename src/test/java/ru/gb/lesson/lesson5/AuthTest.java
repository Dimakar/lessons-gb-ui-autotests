package ru.gb.lesson.lesson5;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthTest extends BasicTest{


    @Test
    void authWithUiTest() {
        webDriver.get("http://the-internet.herokuapp.com/login");

        webDriver.findElement(By.name("username")).sendKeys("tomsmith");
        webDriver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Secure Area')]")));
    }

    @Test
    void authWithCookieTest() {
        webDriver.get("http://the-internet.herokuapp.com/login");

        webDriver.manage().deleteAllCookies();
        webDriver.manage().addCookie(new Cookie("rack.session", "BAh7CkkiD3Nlc3Npb25faWQGOgZFVEkiRWMzODEzZTQ5YmYxYzZkN2I4Njk3%0AZWMxNTBiMjdkOWFmZTU1N2JhNzJhZWYxY2NkYzliN2U4M2NiZjQ0NmVhMDMG%0AOwBGSSIJY3NyZgY7AEZJIiVlNWMzOGRhNTIyNzAyOGQ3MWZhYmU4OTZkMTcy%0ANDE3OAY7AEZJIg10cmFja2luZwY7AEZ7B0kiFEhUVFBfVVNFUl9BR0VOVAY7%0AAFRJIi1jNjk3MTM0YWMxNTg5Y2ZmNzFmNjA0YTc1ODhmYjFhMmIxMDkxMjYz%0ABjsARkkiGUhUVFBfQUNDRVBUX0xBTkdVQUdFBjsAVEkiLWM2OWVjOTEzYTg1%0AY2UyMmNjNmM4NjJmYWRlZjdmNWFhMmM2M2JmODkGOwBGSSIKZmxhc2gGOwBG%0AewBJIg11c2VybmFtZQY7AEZJIg10b21zbWl0aAY7AFQ%3D%0A--f28939b33b3c53099bed29b847ea6cf4083aa28b"));
        webDriver.get("http://the-internet.herokuapp.com/secure");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Secure Area')]")));

    }


    @Test
    void basicAuthTest() {
        webDriver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Congratulations! You must have the proper credentials.')]")));
    }
}
