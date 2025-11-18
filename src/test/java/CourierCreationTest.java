import Steps.CourierCreation.PostMainSteps;
import Steps.CourierCreation.StepsMessage;
import Steps.CourierCreation.StepsResponse;
import Steps.General.GeneralSteps;
import Steps.General.StatusCodeSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CourierCreationTest extends PostMainSteps {

    private static Stream<Arguments> positiveCredentialsProvider() {
        return Stream.of(
                Arguments.of("Maksimusus", "578242", "Максимус"),
                Arguments.of("Maximuman", "12452String", "Энерджайзер")
        );
    }

    private static Stream<Arguments> negativeCredentialsProvider() {
        return Stream.of(
                Arguments.of("Maksimusus", "", "Максимус"),
                Arguments.of("", "12452String", "Энерджайзер")
        );
    }

    private StepsMessage stepsMessage;
    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    public void initStepsConstructors(){
        stepsMessage = new StepsMessage();
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @AfterEach
    public void deleteNewCourier() {
        loginAndDeleteCourierIfExists();
    }

    @ParameterizedTest
    @MethodSource("positiveCredentialsProvider")
    @DisplayName("Создание курьера")
    public void newCourierCreation(String login, String password, String firstName) {

        Response response = sendPostRequestCourier(login, password, firstName);
        statusCodeSteps.checkSuccessfulCodeStatus201(response);
        stepsResponse.checkSuccessfulResponse(response);
        generalSteps.printResponseBodyToConsole(response);

    }

    @ParameterizedTest
    @MethodSource("positiveCredentialsProvider")
    @DisplayName("Повторное создание курьера")
    public void repeatCourierCreation(String login, String password, String firstName) {

        // Первый запрос – курьер создаётся успешно
        Response firstResponse = sendPostRequestCourier(login, password, firstName);
        statusCodeSteps.checkSuccessfulCodeStatus201(firstResponse);
        stepsResponse.checkSuccessfulResponse(firstResponse);
        generalSteps.printResponseBodyToConsole(firstResponse);

        // Второй запрос – тот же логин, ожидаем 409
        Response secondResponse = sendPostRequestCourier(login, password, firstName);
        statusCodeSteps.checkNegativeStatusCode409(secondResponse);
        stepsMessage.checkUsingRepeatMessage(secondResponse);
        generalSteps.printResponseBodyToConsole(secondResponse);
    }

    @ParameterizedTest
    @MethodSource("negativeCredentialsProvider")
    @DisplayName("Проверка обязательных полей")
    public void courierCreationWithoutRequiredFields(String login, String password, String firstName){
        Response response = sendPostRequestCourier(login, password, firstName);
        statusCodeSteps.checkNegativeStatusCodeWithoutRequiredFields400(response);
        stepsMessage.checkMessageWithoutRequiredFields(response);
        generalSteps.printResponseBodyToConsole(response);
    }
}
