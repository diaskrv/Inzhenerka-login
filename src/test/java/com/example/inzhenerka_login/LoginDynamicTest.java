package com.example.inzhenerka_login;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class LoginDynamicTest extends BaseTest {

    record UserAccount(String role, String login, String password, String expectedText) {}

    private final LoginPage loginPage = new LoginPage();

    @TestFactory
    Stream<DynamicTest> loginTestsForDifferentRoles() {
        List<UserAccount> users = List.of(
                new UserAccount("АДмин", "admin", "admin123", "Привет, admin"),
                new UserAccount("Юзер", "user", "user123", "Привет, user"),
                new UserAccount("Гость", "guest", "guest123", "Привет, guest")
        );

        return users.stream().map(user ->
                dynamicTest("Тест для: " + user.role(), () -> {
                    loginPage.openLoginPage();
                    loginPage.setUsername(user.login());
                    loginPage.setPassword(user.password());
                    loginPage.login();
                    loginPage.checkWelcomeText(user.expectedText());
                })
        );
    }
}