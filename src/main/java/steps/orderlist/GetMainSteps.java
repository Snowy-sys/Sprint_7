package steps.orderlist;

import baseclass.RestBaseClass;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetMainSteps extends RestBaseClass {

    @Step("Получить список заказов по эндпоиту /api/v1/orders")
    public Response sendGetRequestListOrder(){

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .get(apiOrderCreate);
        return response;
    }

}
