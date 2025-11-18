package Steps.CourierLogIn;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StepsResponse {

    @Step("Проверка наличия ID курьера")
    public void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("id", notNullValue());
    }

}
