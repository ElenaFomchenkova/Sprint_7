package ru.yandex.praktikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.dto.OrderRequest;


public class OrderRequestGenerator {

    public static OrderRequest getRandomOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        orderRequest.setAddress(RandomStringUtils.randomAlphabetic(5));
        orderRequest.setColor(new String[]{"GREY", "BLACK"});
        orderRequest.setComment(RandomStringUtils.randomAlphabetic(20));
        orderRequest.setDeliveryDate("2023-06-06");
        orderRequest.setLastName(RandomStringUtils.randomAlphabetic(10));
        orderRequest.setMetroStation(RandomStringUtils.random(2, false, true));
        orderRequest.setPhone(RandomStringUtils.random(10, false, true));
        orderRequest.setRentTime(5);
        return orderRequest;
    }
}
