package ru.gb.lesson.lesson8.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.gb.lesson.lesson8.pages.LoginPage;
import ru.gb.lesson.lesson8.pages.MyAccountPage;
import ru.gb.lesson.lesson8.pages.ProductPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyStepDefs {

    @Given("Login Page is loaded")
    public void loginPageIsLoaded() {
        open("http://automationpractice.com/index.php?controller=authentication&back=identity");
    }

    @When("User input {string} and {string}")
    public void userInputAnd(String arg0, String arg1) {
        page(LoginPage .class).login(arg0, arg1);
    }

    @And("User go to page {string} -> {string}")
    public void userGoToPage(String arg0, String arg1) {
        page(MyAccountPage.class)
                .getHeaderBlock()
                .goToPage(arg0, arg1);
    }

    @And("User select product with number {string}")
    public void userSelectProductWithNumber(String arg0) {
        page(ProductPage.class)
                .selectProduct(Integer.parseInt(arg0));
    }

    @Then("Product is added to the cart")
    public void productIsAddedToTheCart() {
        page(ProductPage.class)
                .checkProductAdded();
    }

    @And("User click {string}")
    public void userClick(String arg0) {
        $(byText(arg0)).click();
    }
}
