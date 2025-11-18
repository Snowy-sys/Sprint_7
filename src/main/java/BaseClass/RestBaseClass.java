package BaseClass;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class RestBaseClass {

    public String apiCourierLoginInSystem = "/api/v1/courier/login";
    public String apiCourierCreation = "/api/v1/courier";
    public String apiCourierDelete = "/api/v1/courier/";

    public String apiOrderCreate = "/api/v1/orders";


    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }



}
