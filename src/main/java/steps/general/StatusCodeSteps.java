package steps.general;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StatusCodeSteps {
    @Step("Проверка негативного статус кода 409")
    public void checkNegativeStatusCode409(Response response) {
        response.then().statusCode(409);
    }

    @Step("Проверка негативного статус кода 400")
    public void checkNegativeStatusCodeWithoutRequiredFields400(Response response){
        response.then().statusCode(400);
    }

    @Step("Проверка статуса кода 201")
    public void checkSuccessfulCodeStatus201(Response response){
        response.then().statusCode(201);
    }

    @Step("Проверка статуса кода 200")
    public void checkSuccessfulCodeStatus200(Response response){
        response.then().statusCode(200);
    }

    @Step("Проверка статуса кода 404")
    public void checkCodeStatusWithErrorInFields404(Response response){
        response.then().statusCode(404);
    }

}
