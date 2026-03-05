package com.example.inzhenerka_login;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ApiTests {

    @Test
    void getUserTestuser2() {
        RestAssured.baseURI = "http://qa-stand-login.inzhenerka.tech/api";
        given()
                .queryParam("API_KEY", "API_KEY123")
                .when()
                .get("/users/testuser2")
                .then()
                .statusCode(200)
                .body("username", equalTo("testuser2"));
    }

    @Test
    void getAdminUser() {
        RestAssured.baseURI = "http://qa-stand-login.inzhenerka.tech/api";
        given()
                .queryParam("API_KEY", "API_KEY123")
                .when()
                .get("/users/admin")
                .then()
                .statusCode(200)
                .body("username", equalTo("admin"));
    }
}