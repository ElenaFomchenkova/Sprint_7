import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.OrderListClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GettingListOfOrdersTest {

    private OrderListClient orderListClient;
@Before
    public void setUp(){
    orderListClient = new OrderListClient();
}

@Test
@DisplayName("Get list of orders")
    public void getOrderListTest(){
orderListClient.getListOfOrders().assertThat().statusCode(SC_OK)
        .body("orders", notNullValue());
}
}
