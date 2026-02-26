package com.example.inzhenerka_login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InzhenerkaLoginTest {

    @Test
    @DisplayName("Проверка авторизации и выхода из системы")
    public void runLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.setUsername("user");
        loginPage.setPassword("user123");
        loginPage.login();
        loginPage.checkWelcomeText("Привет, user");
        loginPage.logout();
    }
}

