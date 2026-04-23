package com.example.inzhenerka_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class TicketonTestFields {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://ticketon.kz";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void closeBanner() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        open("/");
        var closeButton = $("[class^='MainBannerModal_closeIcon']");

        if (closeButton.is(Condition.visible)) {
            closeButton.click();
            closeButton.shouldBe(Condition.hidden);
        }
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("languageProvider")
    void testHtmlLangAttribute(String btnText, String expectedLang, String expectedText) {
        var langList = $("[class^='Languages_wrapperLanguagesBtn']");
        langList.click();

        $("[class^='DropdownList_scrollWrapper']").shouldBe(Condition.visible);
        $(byText(btnText)).click();
        $("[class^='DropdownList_scrollWrapper']").shouldBe(Condition.hidden);

        $("html").shouldHave(Condition.attribute("lang", expectedLang));
        $("body").shouldHave(Condition.text(expectedText));
    }

    //У сайта Ticketon значение lang в атрибуте html никак не меняется и остается как ru, что не совсем является корректным
    static Stream<Arguments> languageProvider() {
        return Stream.of(
                Arguments.of("Eng", "ru", "Event"),
                Arguments.of("Рус", "ru", "событий"),
                Arguments.of("Қаз", "ru", "Оқиғалар")
        );
    }
    @ParameterizedTest
    @CsvSource(value = {
            "Астана",
            "Алматы",
            "Караганда"
    })
    void testCitySelectionFilter(String city) {
        var cityButton = $("[class^='CitySelect_markerClickableArea']");
        cityButton.shouldBe(Condition.visible).click();

        var dropdown = $("[class^='DropdownList_dropdownList']").shouldBe(Condition.visible);

        dropdown.$(withText(city)).closest("li").click();

        $("[data-testid='city-selector-trigger']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.text(city));
    }

    @Disabled
    @Test
    void testSearchFieldClear() {
        open("/");

        var searchInput = $(By.xpath("//input[@placeholder='Поиск событий']"));

        searchInput.setValue("Saluki");
        searchInput.shouldHave(Condition.value("Saluki"));

        searchInput.clear();

        searchInput.shouldBe(Condition.empty);
    }
}
