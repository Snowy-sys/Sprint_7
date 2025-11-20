package steps.courierlogin;

import baseclass.RestBaseClass;
import pojomodels.CourierLoginPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostMainSteps extends RestBaseClass {
    protected String login;
    protected String password;

    @Step("Получить ID курьера в системе по эндпоиту /api/v1/courier/login")
    public Response sendPostRequestCourierLogin(String login, String password){

        CourierLoginPojo сourierLoginPojo =
                new CourierLoginPojo(login, password);


        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(сourierLoginPojo)
                        .when()
                        .post(apiCourierLoginInSystem);

        return response;
    }


}
