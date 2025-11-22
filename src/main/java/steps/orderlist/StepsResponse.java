package steps.orderlist;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class StepsResponse {

    @Step("Проверка наличия объекта orders в ответе")
    public void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("orders", notNullValue());
    }

}
