package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.dto.CourierRequest;
import ru.yandex.praktikum.dto.LoginRequest;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestClient{
    //create
    @Step("Send POST request to /api/v1/courier")
    public ValidatableResponse create(CourierRequest courierRequest) {
       return given()
               .spec(getDefaultRequestSpec())
                .body(courierRequest)
                .post("courier")
               .then();
    }
    //login
    @Step("Send POST request to /api/v1/courier/login")
    public ValidatableResponse login(LoginRequest loginRequest){
        return given()
                .spec(getDefaultRequestSpec())
                .body(loginRequest)
                .post("courier/login")
                .then();
    }

    //delete
    @Step("Send DELETE request to /api/v1/courier/id")
    public ValidatableResponse delete(Integer id) {
        return given()
                .spec(getDefaultRequestSpec())
                .delete(String.format("courier/%d", id))
                .then();
    }

}
