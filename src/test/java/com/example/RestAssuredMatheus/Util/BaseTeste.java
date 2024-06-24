package com.example.RestAssuredMatheus.Util;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

public class BaseTeste {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
}

