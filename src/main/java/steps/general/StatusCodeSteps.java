package steps.general;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;

public class StatusCodeSteps {
    @Step("Проверка негативного статус кода 409")
    public void checkNegativeStatusCode409(Response response) {
        response.then().statusCode(SC_CONFLICT);
    }

    @Step("Проверка негативного статус кода 400")
    public void checkNegativeStatusCodeWithoutRequiredFields400(Response response){
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @Step("Проверка статуса кода 201")
    public void checkSuccessfulCodeStatus201(Response response){
        response.then().statusCode(SC_CREATED);
    }

    @Step("Проверка статуса кода 200")
    public void checkSuccessfulCodeStatus200(Response response){
        response.then().statusCode(SC_OK);
    }

    @Step("Проверка статуса кода 404")
    public void checkCodeStatusWithErrorInFields404(Response response){
        response.then().statusCode(SC_NOT_FOUND);
    }

}
