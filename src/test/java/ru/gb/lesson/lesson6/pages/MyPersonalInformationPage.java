package ru.gb.lesson.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class MyPersonalInformationPage extends BasicView {

    @FindBy(name = "firstname")
    public WebElement firstName;

    @FindBy(name = "old_passwd")
    public WebElement oldPassword;

    @FindBy(name = "submitIdentity")
    public WebElement submitIdentity;

    @FindBy(xpath = "//a[contains(.,'Back to your account')]")
    public WebElement backToAccount;

    public MyPersonalInformationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyPersonalInformationPage changeFirstName(String name, String password) {
        firstName.clear();
        firstName.sendKeys(name);
        oldPassword.sendKeys(password);
        submitIdentity.click();
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your personal information has been successfully updated.')]")));
        return this;
    }

    public MyAccountPage backToAccount() {
        backToAccount.click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage checkFirstName(String name) {
        assertThat(firstName.getAttribute("value"))
                .isEqualTo(name);
        return new MyAccountPage(webDriver);
    }
}
