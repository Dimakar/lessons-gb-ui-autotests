package ru.gb.lesson.lesson8.stepdefs;

import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;

import org.junit.runner.RunWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@RunWith(Cucumber.class)
public class Hooks {

    @After
    public void tearDown() throws Exception {
        closeWebDriver();
    }
}
