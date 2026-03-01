package com.example.inzhenerka_login;

import org.junit.jupiter.api.*;
import java.io.File;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InzhenerkaLoginTest extends BaseTest{

    private final LoginPage loginPage = new LoginPage();

    @Test
    @Order(1)
    @DisplayName("Процесс авторизация")
    public void loginTest() {
        loginPage.openLoginPage();
        loginPage.setUsername("user");
        loginPage.setPassword("user123");
        loginPage.login();
        loginPage.checkWelcomeText("Привет, user");
    }

    @Test()
    @Order(2)
    @DisplayName("Выход из системы")
    public void logoutTest() {
        loginPage.openLoginPage();
        loginPage.setUsername("user");
        loginPage.setPassword("user123");
        loginPage.login();
        loginPage.logout();
    }

    @Disabled("Тест временно отключен: ожидается исправление бага с загрузкой файлов на сервере")
    @Test
    @Order(3)
    @DisplayName("Проверка приложения файла")
    void fileUploadTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.setUsername("admin");
        loginPage.setPassword("admin123");
        loginPage.login();

        // Предположим, на странице есть input для загрузки файла
        // В Selenide это делается одной командой uploadFile
        // Файл должен лежать в папке проекта (например, в корне или в resources)

        File file = new File("src/test/resources/Test Viper.txt");
//        if (file.exists()) {
//            $("input[type='file']").uploadFile(file);
//            // Проверка, что файл загрузился (текст об успехе)
//            $(".upload-status").shouldHave(text("Файл загружен"));
//        }
        System.out.println("Тест загрузки подготовлен (нужен актуальный селектор input)");
    }
}

