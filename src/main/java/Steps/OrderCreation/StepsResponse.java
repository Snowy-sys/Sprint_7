package Steps.OrderCreation;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class StepsResponse {

    @Step("Проверка наличия trackID заказа")
    public void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("track", notNullValue());
    }
}
