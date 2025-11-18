package Steps.CourierCreation;

import BaseClass.RestBaseClass;
import PojoModels.CourierCreationPojo;
import PojoModels.CourierLoginPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostMainSteps extends RestBaseClass {
    protected String login;
    protected String password;
    protected Integer courierId;

    @Step("Создать курьера по эндпоинту /api/v1/courier")
    public Response sendPostRequestCourier(String login, String password, String firstName){

        this.login = login;
        this.password = password;

        CourierCreationPojo courierCreationPojo =
                new CourierCreationPojo(login, password, firstName);

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierCreationPojo)
                        .when()
                        .post(apiCourierCreation);

        return response;

    }

    @Step("Получить ID курьера в системе по эндпоиту /api/v1/courier/login")
    public Response sendPostRequestCourierLogin(){

        CourierLoginPojo сourierLoginPojo =
                new CourierLoginPojo(login, password);


        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(сourierLoginPojo)
                        .when()
                        .post(apiCourierLoginInSystem);

        Integer id = response.jsonPath().get("id");  // вернёт null, если поля нет

        if (id != null) {
            this.courierId = id;
        }

        return response;
    }

    @Step("Удалить курьера в системе по эндпоиту /api/v1/courier/:id")
    public Response sendDeleteRequestCourier(){

        if (courierId == null) {
            throw new IllegalStateException("Courier ID is null. Сначала авторизуйтесь!");
        }

        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(apiCourierDelete + courierId);

    }

    @Step("Авторизация и удаление курьера, если он существует")
    public void loginAndDeleteCourierIfExists() {
        Response loginResponse = sendPostRequestCourierLogin();
        Integer id = loginResponse.jsonPath().get("id");

        if (id != null) {
            this.courierId = id;
            sendDeleteRequestCourier();
        }
    }


}
