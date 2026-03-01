package com.example.inzhenerka_login;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArithmeticTest {

    // Пример с @CsvSource для проверки разных операций
    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "10, +, 5, 15",
            "20, -, 8, 12",
            "5, *, 4, 20",
            "10, /, 2, 5"
    })
    void testCalculations(int a, String operation, int b, int expected) {
        int result = 0;
        switch (operation) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
        }
        assertEquals(expected, result);
    }

    // Пример с @ValueSource для проверки только четных чисел
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 10, 100})
    void testIsEven(int number) {
        assertTrue(number % 2 == 0, "Число должно быть четным");
    }
}