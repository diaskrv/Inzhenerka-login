package com.example.inzhenerka_login;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class InzhenerkaLoginTest {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        // Configuration.holdBrowserOpen = true; // Полезно для отладки
    }

    @BeforeEach
    void openLoginPage() {
        open("http://qa-stand-login.inzhenerka.tech/login");
    }

    // --- ЗАДАНИЕ 1 & 2: Разные локаторы и проверки видимости ---
    @Test
    void loginWithVariousLocatorsTest() {
        // CSS селектор (ID)
        $("#username").shouldBe(visible).setValue("admin");

        // XPath (Поиск по атрибуту name)
        $(By.xpath("//input[@name='password']")).shouldBe(visible).setValue("admin123");

        // Поиск через стандартный Selenium By селектор (тег)
        $(By.tagName("button")).shouldHave(text("Войти")).click();

        // Проверка успешного входа
        $("body").shouldHave(text("Привет, admin"));
        $(byText("Выйти")).shouldBe(visible).click();
    }

    @Test
    void loginUsingOnlyTextSelectorsTest() {
        // Использование только текстовых локаторов (Selenide Selectors)
        // ВАЖНО: Мы ищем элементы, которые содержат или имеют такой текст
        $(withText("Логин")).parent().find("input").setValue("admin");
        $(withText("Пароль")).parent().find("input").setValue("admin123");

        $(byText("Войти")).shouldBe(enabled).click();

        // Проверка и выход
        $(withText("Привет")).shouldBe(visible);
        $(byText("Выйти")).click();
    }

    // --- ЗАДАНИЕ 3: Работа с файлами ---
    @Test
    void fileUploadTest() {
        // 1. Сначала нужно войти (файлы обычно доступны внутри системы)
        $("#username").setValue("admin");
        $(By.name("password")).setValue("admin123");
        $("button[type='submit']").click();

        // Предположим, на странице есть input для загрузки файла
        // В Selenide это делается одной командой uploadFile
        // Файл должен лежать в папке проекта (например, в корне или в resources)

        /* File file = new File("src/test/resources/test_doc.txt");
        if (file.exists()) {
            $("input[type='file']").uploadFile(file);
            // Проверка, что файл загрузился (текст об успехе)
            $(".upload-status").shouldHave(text("Файл загружен"));
        }
        */
        System.out.println("Тест загрузки подготовлен (нужен актуальный селектор input)");
    }

    // --- ЗАДАНИЕ 4: Условные действия (If-Else) ---
    @Test
    void conditionalActionTest() {
        // Пытаемся залогиниться
        $("#username").setValue("admin");
        $(By.name("password")).setValue("admin123");
        $("button[type='submit']").click();

        // Проверяем наличие элемента без падения теста (используем .is() или .exists())
        SelenideElement logoutButton = $(byText("Выйти"));

        if (logoutButton.is(visible)) {
            System.out.println("Авторизация успешна, нажимаю выход...");
            logoutButton.click();
        } else {
            // Если кнопки нет — выводим ошибку в консоль или кидаем исключение
            System.err.println("ОШИБКА: Кнопка 'Выход' не появилась. Возможно, логин не удался.");
            // Можно принудительно провалить тест с понятным сообщением
            throw new AssertionError("Элемент 'Выйти' не найден после попытки входа");
        }

        // Финальная проверка
        $("button[type='submit']").shouldBe(visible);
    }
}