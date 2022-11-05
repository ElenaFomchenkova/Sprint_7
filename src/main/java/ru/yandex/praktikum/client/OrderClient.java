package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.dto.OrderRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {
    @Step("Send POST request to /api/v1/orders")
    public ValidatableResponse createOrder(OrderRequest orderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(orderRequest)
                .post("orders")
                .then();
    }
}
