package com.example.inzhenerka_login;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        // Configuration.holdBrowserOpen = true; // Полезно для отладки
    }

    @AfterEach
    //Logging out after every test
    public void tearDownTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logout();
    }
}
