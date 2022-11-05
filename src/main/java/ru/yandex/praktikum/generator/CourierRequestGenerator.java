package ru.yandex.praktikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.dto.CourierRequest;

public class CourierRequestGenerator {
    public static CourierRequest getRandomCourier() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        return courierRequest;
    }

    public static CourierRequest getCourierWithoutFirstName() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        return courierRequest;
    }

    public static CourierRequest getCourierWithoutPassword() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        return courierRequest;
    }

    public static CourierRequest getCourierWithoutLogin(){
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        courierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        return courierRequest;
    }
}
