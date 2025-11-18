package Steps.CourierCreation;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class StepsResponse {
    @Step("Проверка ответа ok:true")
    public void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("ok", equalTo(true));
    }

}
