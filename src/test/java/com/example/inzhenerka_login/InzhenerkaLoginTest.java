package com.example.inzhenerka_login;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InzhenerkaLoginTest {

    @BeforeAll
    static void setup() {
        // Это выведет подробные шаги в консоль и подготовит данные для отчетов
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "firefox"; // Раскомментируй, если нужен Firefox
    }

    @BeforeEach
    void openLoginPage() {
        // Этот код выполнится ПЕРЕД КАЖДЫМ тестом ниже
        open("http://qa-stand-login.inzhenerka.tech/login");
    }

    @Test
    void loginAndLogoutTestWithCredentials() {
        // 1. Вводим логин и пароль (данные берем из документации)
        $("#username").setValue("admin");
        $(By.name("password")).setValue("admin123"); // <-- Проверь пароль в документации!

        // 2. Нажимаем "Вход"
        $("button[type='submit']").click();

        // 3. ПРОВЕРКА (Assertion)
        // Мы ищем элемент body или заголовок, который содержит приветствие
        $("body").shouldHave(text("Привет, admin"));

        // 4. Выход из системы
        // Используем поиск по тексту "Выйти" — это надежнее, чем искать по классам стилей (.mb-3)
        $(byText("Выйти")).click();

        // 5. Проверка выхода (снова видна кнопка "Вход")
        $("button[type='submit']").shouldBe(visible);

    }

        @Test
        void loginAndLogoutTestWithoutCredentials() {
        // 1. Очищаем поле логина (так как там мог остаться старый ввод)
        $("#username").clear();
        // Можно также использовать setValue(""), Selenide сам очистит поле
        $(By.name("password")).setValue("");

        // 2. Нажимаем кнопку "Вход"
        $("button[type='submit']").click();

        // 3. ПРОВЕРКА: Система не должна нас впустить
        // Кнопка входа всё еще должна быть видна
        $(byText("Выйти")).shouldBe(visible);

        $(byText("Выйти")).click();
        $("button[type='submit']").shouldBe(visible);
    }
}
