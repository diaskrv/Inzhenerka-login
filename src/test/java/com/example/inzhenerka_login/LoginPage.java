package com.example.inzhenerka_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage{

    private final SelenideElement loginField = $("#username");
    private final SelenideElement passwordField = $(By.name("password"));
    private final SelenideElement loginButton = $("button[type='submit']");
    private final SelenideElement logoutButton = $(byText("Выйти"));
    private final SelenideElement welcomeMessage = $(".card-title");

    void openLoginPage() {
        open("http://qa-stand-login.inzhenerka.tech/login");
    }

    public void setUsername(String username) {
        loginField.setValue(username);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void login() {
        loginButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    public void checkWelcomeText(String expectedText) {
        welcomeMessage.shouldHave(Condition.text(expectedText));
    }
}