import Steps.CourierLogIn.PostMainSteps;
import Steps.CourierLogIn.StepsMessage;
import Steps.CourierLogIn.StepsResponse;
import Steps.General.GeneralSteps;
import Steps.General.StatusCodeSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CourierLoginTest extends PostMainSteps {

    private static Stream<Arguments> positiveCredentialsProvider() {
        return Stream.of(
                Arguments.of("MaksimkaNevidimka", "12356"),
                Arguments.of("LenkaPolenka", "12345String")
        );
    }

    private static Stream<Arguments> negativeCredentialsProvider() {
        return Stream.of(
                Arguments.of("MaksimkaNevidimk", "12356"),
                Arguments.of("MaksimkaNevidimka", "1235"),
                Arguments.of("", "12345String"),
                Arguments.of("LenkaPolenka", ""),
                Arguments.of("", "")
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

    @ParameterizedTest
    @MethodSource("positiveCredentialsProvider")
    @DisplayName("Авторизация курьера с валидными данными")
    public void courierLogIn(String login, String password) {

        Response response = sendPostRequestCourierLogin(login, password);
        statusCodeSteps.checkSuccessfulCodeStatus200(response);
        stepsResponse.checkSuccessfulResponse(response);
        generalSteps.printResponseBodyToConsole(response);


    }

    @ParameterizedTest
    @MethodSource("negativeCredentialsProvider")
    @DisplayName("Авторизация курьера с невалидными данными")
    public void courierLogInWithoutRequiredFields(String login, String password) {

        Response response = sendPostRequestCourierLogin(login, password);

        if (login.isEmpty() || password.isEmpty()) {
            statusCodeSteps.checkNegativeStatusCodeWithoutRequiredFields400(response);
            stepsMessage.checkMessageWithoutRequiredFields(response);
        } else {
            statusCodeSteps.checkCodeStatusWithErrorInFields404(response);
            stepsMessage.checkMessageWithErrorInFields(response);
        }

        generalSteps.printResponseBodyToConsole(response);


    }


}
