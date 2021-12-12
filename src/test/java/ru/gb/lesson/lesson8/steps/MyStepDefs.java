package ru.gb.lesson.lesson8.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.gb.lesson.lesson8.pages.LoginPage;
import ru.gb.lesson.lesson8.pages.MyAccountPage;
import ru.gb.lesson.lesson8.pages.ProductPage;

import static com.codeborne.selenide.Selenide.page;

public class MyStepDefs {
    @Given("User {string} {string} is authorized")
    public void userIsAuthorized(String arg0, String arg1) {
        page(LoginPage.class).login(arg0, arg1);
    }

    @When("User go ro page {string} -> {string}")
    public void userGoRoPage(String arg0, String arg1) {
        page(MyAccountPage.class).getHeaderBlock().goToPage(arg0, arg1);
    }

    @And("User select {string}")
    public void userSelect(String arg0) {
        page(ProductPage.class).selectProduct(Integer.parseInt(arg0));
    }

    @Then("Product is added to the cart")
    public void productIsAddedToTheCart() {
        page(ProductPage.class).checkProductAdded();
    }

    @Given("Login page is opened")
    public void loginPageIsOpened() {
        Selenide.open("http://automationpractice.com/index.php?controller=authentication&back=identity");
    }
}
