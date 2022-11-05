import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.OrderClient;
import ru.yandex.praktikum.dto.OrderRequest;

import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.generator.OrderRequestGenerator.getRandomOrder;

public class CreateOrderTest {
private OrderClient orderClient;
private int track;
@Before
        public void setUp() {
    orderClient = new OrderClient();
}

@Test
@DisplayName("API POST /api/v1/orders testing creating orders")
        public void createOrderTest() {
    OrderRequest orderRequest = getRandomOrder();
    track = orderClient.createOrder(orderRequest).assertThat().statusCode(HttpStatus.SC_CREATED)
        .and().body("track", notNullValue())
        .extract().path("track");
    System.out.println(track);
}
}
