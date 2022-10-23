import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.dto.CourierRequest;
import ru.yandex.praktikum.dto.LoginRequest;
import ru.yandex.praktikum.generator.LoginRequestGenerator;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.generator.CourierRequestGenerator.*;

public class CourierCreateTest {

    private CourierClient courierClient;
    private Integer id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .assertThat()
                    .body("ok", equalTo(true));
        }
    }

    @Test
    @DisplayName("API POST /api/v1/courier testing creation courier")
    public void courierShouldBeCreatedTest() {
        //create
        CourierRequest courierRequest = getRandomCourier();

        courierClient.create(courierRequest).assertThat().statusCode(SC_CREATED)
                .and().body("ok", equalTo(true));

//login
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
    @DisplayName("API POST /api/v1/courier testing creation same couriers")
    public void sameCourierShouldNotBeCreatedTest() {
        CourierRequest courierRequest = getRandomCourier();

        courierClient.create(courierRequest).assertThat().statusCode(SC_CREATED)
                .and().body("ok", equalTo(true));


        LoginRequest loginRequest = LoginRequestGenerator.from(courierRequest);
        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
        System.out.println(id);

        courierClient.create(courierRequest).assertThat().statusCode(SC_CONFLICT);
    }

    @Test
    @DisplayName("API POST /api/v1/courier testing creation courier without first name")
    public void CourierCreatedWithoutFirstNameTest() {
        CourierRequest courierRequest = getCourierWithoutFirstName();
        courierClient.create(courierRequest).assertThat().statusCode(SC_CREATED)
                .and().body("ok", equalTo(true));

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
    @DisplayName("API POST /api/v1/courier testing creation courier without password")
    public void courierShouldNotBeCreatedWithoutPasswordTest() {
        CourierRequest courierRequest = getCourierWithoutPassword();
        courierClient.create(courierRequest).assertThat().statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("API POST /api/v1/courier testing creation courier without login")
    public void courierShouldNonBeCreatedWithoutLoginTest(){
        CourierRequest courierRequest = getCourierWithoutLogin();
        courierClient.create(courierRequest).assertThat().statusCode(SC_BAD_REQUEST);
    }
}
