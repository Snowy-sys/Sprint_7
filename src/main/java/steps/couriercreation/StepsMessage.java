package steps.couriercreation;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class StepsMessage {

    @Step("Проверка сообщения о том, что логин уже используется")
    public void checkUsingRepeatMessage(Response response) {
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Проверка сообщения о недостаточности данных")
    public void checkMessageWithoutRequiredFields(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
