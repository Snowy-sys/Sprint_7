import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;;import static io.restassured.RestAssured.given;

public class RestBaseClass {

    public String apiCourierLoginInSystem = "/api/v1/courier/login";
    public String apiCourierCreation = "/api/v1/courier";
    public String apiCourierDelete = "/api/v1/courier/";

    protected Integer courierId;
    protected String nameOfCourier;
    protected String passwordOfCourier;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

}
