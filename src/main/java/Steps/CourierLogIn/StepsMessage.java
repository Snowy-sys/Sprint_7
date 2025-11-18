package Steps.CourierLogIn;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class StepsMessage {

    @Step("Проверка сообщения о недостаточности данных")
    public void checkMessageWithoutRequiredFields(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Проверка сообщения о том, что УЗ не существует")
    public void checkMessageWithErrorInFields(Response response) {
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

}
