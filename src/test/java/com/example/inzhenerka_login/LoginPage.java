package com.example.inzhenerka_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BaseTest{

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

//    @Test
//    void fileUploadTest() {
//        // 1. Сначала нужно войти (файлы обычно доступны внутри системы)
//        $("#username").setValue("admin");
//        $(By.name("password")).setValue("admin123");
//        $("button[type='submit']").click();
//
//        // Предположим, на странице есть input для загрузки файла
//        // В Selenide это делается одной командой uploadFile
//        // Файл должен лежать в папке проекта (например, в корне или в resources)
//
//        /* File file = new File("src/test/resources/test_doc.txt");
//        if (file.exists()) {
//            $("input[type='file']").uploadFile(file);
//            // Проверка, что файл загрузился (текст об успехе)
//            $(".upload-status").shouldHave(text("Файл загружен"));
//        }
//        */
//        System.out.println("Тест загрузки подготовлен (нужен актуальный селектор input)");
//    }
}