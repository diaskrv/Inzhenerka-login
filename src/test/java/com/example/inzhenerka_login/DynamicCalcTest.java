package com.example.inzhenerka_login;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import java.util.stream.Stream;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicCalcTest {

    @TestFactory
    Stream<DynamicTest> dynamicTestsForAddition() {
        return Stream.of(1, 2, 3).map(input ->
                dynamicTest("Динамическая проверка числа: " + input, () -> {
                    assertTrue(input > 0);
                })
        );
    }
}