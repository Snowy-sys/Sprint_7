package Steps.OrderCreation;

import BaseClass.RestBaseClass;
import PojoModels.CourierLoginPojo;
import PojoModels.OrderCreationPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostMainSteps extends RestBaseClass {

    @Step("Создать заказ в системе по эндпоиту /api/v1/orders")
    public Response sendPostRequestOrderCreation(String firstName,
                                                 String lastName,
                                                 String address,
                                                 int metroStation,
                                                 String phone,
                                                 int rentTime,
                                                 String deliveryDate,
                                                 String comment,
                                                 String[] color){

        OrderCreationPojo orderCreationPojo =
                new OrderCreationPojo(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(orderCreationPojo)
                        .when()
                        .post(apiOrderCreate);

        return response;
    }

}
