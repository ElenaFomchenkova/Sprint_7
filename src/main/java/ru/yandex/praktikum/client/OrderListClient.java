package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderListClient extends RestClient {
    @Step("Send GET request to /api/v1/orders")
    public ValidatableResponse getListOfOrders() {
        return given()
                .spec(getDefaultRequestSpec())
                .get("orders")
                .then();
    }
}
