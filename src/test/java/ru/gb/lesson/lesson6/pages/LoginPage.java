package ru.gb.lesson.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasicView {

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "passwd")
    public WebElement passwd;

    @FindBy(id = "SubmitLogin")
    public WebElement submitLogin;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyAccountPage login(String email, String password) {
        this.email.sendKeys(email);
        passwd.sendKeys(password);
        submitLogin.click();
        return new MyAccountPage(webDriver);
    }
}
