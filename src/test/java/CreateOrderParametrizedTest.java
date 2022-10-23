import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.OrderClient;
import ru.yandex.praktikum.dto.OrderRequest;


import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

     OrderRequest orderRequest;
     OrderClient orderClient;
int track;

public CreateOrderParametrizedTest(String firstName, String lastName, String address, String metroStation, String phone,
                              int rentTime, String deliveryDate, String comment, String[] color) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.metroStation = metroStation;
    this.phone = phone;
    this.rentTime = rentTime;
    this.deliveryDate = deliveryDate;
    this.comment = comment;
    this.color = color;
}
 @Parameterized.Parameters
 public static Object [][] getTestData(){
     return new Object[][]{
             {"Андрей", "Иванов", "Невский проспект, 35, 72", "7", "+7 900 888 55 11", 3, "2023-08-06", "Привозите вечером",
                     new String[]{"BLACK"}},
             {RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(5),
                     RandomStringUtils.randomAlphabetic(20), RandomStringUtils.random(2,false,true),
                     RandomStringUtils.random(10, false, true), 5, "2022-12-31",
                     RandomStringUtils.randomAlphabetic(50), new String[]{"GREY","BLACK"}},
             {"Иван", "НовыйПользователь", "Москва, 3-я улица Строителей, 15, 27", "4", "+7 777 777 77 77", 7, "2023-02-28",
                     "Одевайтесь теплее, на улице холодно",
                     new String[]{"GREY"}},
             {"Анастасия", "UchihaUchihaUchiha", "Далекая далекая галактика", "33", "+9 858 458 22 22", 1,
                     "2023-05-11", "Купите хлеба по дороге, пожалуйста",
                     new String[]{""}},
     };
 }

@Test
@DisplayName("API POST /api/v1/orders testing creating parameterized orders")
    public void createOrderTest() {

        orderRequest = new OrderRequest(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate,
                comment, color);

    orderClient = new OrderClient();
    track = orderClient.createOrder(orderRequest)
        .assertThat().statusCode(SC_CREATED)
        .and().body("track", notNullValue())
        .extract().path("track");
    System.out.println(track);
}
}
