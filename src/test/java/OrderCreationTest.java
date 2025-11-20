import baseclass.RestBaseClass;
import steps.general.GeneralSteps;
import steps.general.StatusCodeSteps;
import steps.ordercreation.PostMainSteps;
import steps.ordercreation.StepsResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderCreationTest extends RestBaseClass {

    private PostMainSteps postMainSteps;
    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    @DisplayName("Инициализация конструкторов с шагами тестов")
    public void initStepsConstructors(){
        postMainSteps = new PostMainSteps();
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @ParameterizedTest
    @MethodSource("orderCreationCredentialsProvider")
    @DisplayName("Создание заказа с валидными данными")
    public void orderCreation(String firstName,
                             String lastName,
                             String address,
                             int metroStation,
                             String phone,
                             int rentTime,
                             String deliveryDate,
                             String comment,
                             String[] color) {

        Response response =
                postMainSteps.sendPostRequestOrderCreation(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
                statusCodeSteps.checkSuccessfulCodeStatus201(response);
                stepsResponse.checkSuccessfulResponse(response);
                generalSteps.printResponseBodyToConsole(response);
    }

}
