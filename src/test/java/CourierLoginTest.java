import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.dto.CourierRequest;
import ru.yandex.praktikum.dto.LoginRequest;
import ru.yandex.praktikum.generator.LoginRequestGenerator;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.generator.CourierRequestGenerator.getRandomCourier;

public class CourierLoginTest {

    private CourierClient courierClient;
    private Integer id;
    private CourierRequest courierRequest;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courierRequest = getRandomCourier();
        courierClient.create(courierRequest).assertThat().statusCode(SC_CREATED)
                .and().body("ok", equalTo(true));
    }

    @After
    public void tearDown() {
        LoginRequest loginRequest1 = LoginRequestGenerator.from(courierRequest);
        id = courierClient.login(loginRequest1)
                .extract()
                .path("id");
        System.out.println(id);
        if (id != null) {
            courierClient.delete(id)
                    .assertThat()
                    .body("ok", equalTo(true));
        }
    }

    @Test
    @DisplayName("API POST /api/v1/courier/login checking login courier")
    public void courierLogInTest() {
        LoginRequest loginRequest = LoginRequestGenerator.from(courierRequest);
        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
        System.out.println(id);
    }

    @Test
    @DisplayName("API POST /api/v1/courier/login checking login courier without login")
    public void courierShouldNotLogInWithoutLoginTest() {
        LoginRequest loginRequest = LoginRequestGenerator.withoutLogin(courierRequest);
        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("id");
        System.out.println(id);
    }

    @Test
    @DisplayName("API POST /api/v1/courier/login checking login courier without password")
    public void courierShouldNotLogInWithoutPasswordTest() {
        //???????? ???????????????? ????-???? ???????????????????????????? ???? ?? ????
        LoginRequest loginRequest = LoginRequestGenerator.withoutPassword(courierRequest);
        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("id");
        System.out.println(id);
    }

    @Test
    @DisplayName("API POST /api/v1/courier/login checking login non existed coirier")
    public void notPossibleToLogInWithNonExistCourierTest() {
        //?????????????????? ?????? ?????????????????? ????????????????
        LoginRequest loginRequest = LoginRequestGenerator.from(courierRequest);
        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
        System.out.println(id);
        //?????????????? ???????????????????? ??????????????
        courierClient.delete(id);
        //?????????????????? ?????? ?????? ????
        courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("?????????????? ???????????? ???? ??????????????"));
    }

}
