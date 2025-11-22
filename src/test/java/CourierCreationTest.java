import baseclass.RestBaseClass;
import steps.couriercreation.PostMainSteps;
import steps.couriercreation.StepsMessage;
import steps.couriercreation.StepsResponse;
import steps.general.GeneralSteps;
import steps.general.StatusCodeSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class CourierCreationTest extends RestBaseClass {

    private PostMainSteps postMainSteps;
    private StepsMessage stepsMessage;
    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    @DisplayName("Инициализация конструкторов с шагами тестов")
    public void initStepsConstructors(){
        postMainSteps = new PostMainSteps();
        stepsMessage = new StepsMessage();
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @AfterEach
    @DisplayName("Удаление курьера после создания и выполнения теста")
    public void deleteNewCourier() {
        postMainSteps.loginAndDeleteCourierIfExists();
    }

    @Test
    @DisplayName("Создание нового курьера")
    public void newCourierCreation() {
        Response response =
                postMainSteps.sendPostRequestCourier(
                        RestBaseClass.TEST_LOGIN,
                        RestBaseClass.TEST_PASSWORD,
                        RestBaseClass.TEST_FIRST_NAME);
        statusCodeSteps.checkSuccessfulCodeStatus201(response);
        stepsResponse.checkSuccessfulResponse(response);
        generalSteps.printResponseBodyToConsole(response);

    }

    @Test
    @DisplayName("Попытка создать курьера повторно с аналогичным логином и паролем")
    public void repeatCourierCreation1() {

        Response firstResponse =
                postMainSteps.sendPostRequestCourier(
                        RestBaseClass.TEST_LOGIN,
                        RestBaseClass.TEST_PASSWORD,
                        RestBaseClass.TEST_FIRST_NAME);
        statusCodeSteps.checkSuccessfulCodeStatus201(firstResponse);
        stepsResponse.checkSuccessfulResponse(firstResponse);
        generalSteps.printResponseBodyToConsole(firstResponse);

        Response secondResponse =
                postMainSteps.sendPostRequestCourier(
                        RestBaseClass.TEST_LOGIN,
                        RestBaseClass.TEST_PASSWORD,
                        RestBaseClass.TEST_FIRST_NAME);
        statusCodeSteps.checkNegativeStatusCode409(secondResponse);
        stepsMessage.checkUsingRepeatMessage(secondResponse);
        generalSteps.printResponseBodyToConsole(secondResponse);
    }

    @ParameterizedTest
    @MethodSource("creationUserCredentialsProvider")
    @DisplayName("Попытка создать курьера без заполнения обязательных полей")
    public void courierCreationWithoutRequiredFields(String login, String password, String firstName){
        Response response =
                postMainSteps.sendPostRequestCourier(login, password, firstName);
                statusCodeSteps.checkNegativeStatusCodeWithoutRequiredFields400(response);
                stepsMessage.checkMessageWithoutRequiredFields(response);
                generalSteps.printResponseBodyToConsole(response);
    }
}
