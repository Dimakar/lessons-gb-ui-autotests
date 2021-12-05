package ru.gb.lesson.lesson6.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gb.lesson.lesson5.BasicTest;
import ru.gb.lesson.lesson6.pages.LoginPage;

import java.util.Locale;

public class PageObjectShopTest extends BasicTest {
    private static final String URL = "http://automationpractice.com/index.php";

    private final String email = "autosupertravel+1117140805004@yandex.ru";
    private final String password = "12345";

    private final Faker faker = new Faker(new Locale("ru"));

    @Test
    @DisplayName("Редактирование профиля: Изменить имя")
    void changeFirstNameTest() {
        String name = faker.name().firstName();

        webDriver.get(URL + "?controller=authentication&back=my-account");

        new LoginPage(webDriver)
                .login(email, password)
                .clickPersonalInformation()
                .changeFirstName(name, password)
                .backToAccount()
                .clickPersonalInformation()
                .checkFirstName(name);
    }
}
