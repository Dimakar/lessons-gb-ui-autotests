package ru.gb.lesson.lesson6;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.gb.lesson.lesson5.BasicTest;
import ru.gb.lesson.lesson6.pages.LoginPage;

public class ShopWithoutPageObjectTest extends BasicTest {
    private final String username = "autosupertravel+1117140805004@yandex.ru";
    private final String password = "12345";
    private final Faker faker = new Faker();

    @Test
    void changeFirstNameTest() {
        String name = faker.name().firstName();

        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");

        new LoginPage(webDriver).login(username, password)
                .clickMyPersonalInformationButton()
                .changeFirstName(name)
                .inputPassword(password)
                .clickSaveButton()
                .clickBackToYourAccountButton()
                .clickMyPersonalInformationButton()
                .checkFirstName(name);
    }


    @Test
    void changeLastNameTest() {
        String name = faker.name().lastName();

        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        new LoginPage(webDriver).login(username, password)
                .clickMyPersonalInformationButton()
                .changeLastName(name)
                .inputPassword(password)
                .clickSaveButton()
                .clickBackToYourAccountButton()
                .clickMyPersonalInformationButton()
                .checkLastName(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Summer Dresses", "T-shirts"})
    void selectSummerDressTest(String secondTab) {
        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        new LoginPage(webDriver).login(username, password)
                .getHeaderBlock()
                .goToPage("Women", secondTab)
                .selectProduct(0)
                .checkProductAdded();
    }
}
