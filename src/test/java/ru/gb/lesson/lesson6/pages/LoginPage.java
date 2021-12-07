package ru.gb.lesson.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement submitButton;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyAccountPage login(String username, String password) {
        emailInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new MyAccountPage(webDriver);
    }
}
