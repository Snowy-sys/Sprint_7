import baseclass.RestBaseClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import steps.courierlogin.PostMainSteps;
import steps.courierlogin.StepsMessage;
import steps.courierlogin.StepsResponse;
import steps.general.GeneralSteps;
import steps.general.StatusCodeSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class CourierLoginTest extends RestBaseClass {

    private PostMainSteps postMainSteps;
    private StepsMessage stepsMessage;
    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    private steps.couriercreation.PostMainSteps postMainStepsCourierCreation;

    @BeforeEach
    @DisplayName("Инициализация конструкторов с шагами тестов и создание нового курьера")
    public void initStepsConstructors(){
        postMainSteps = new PostMainSteps();
        stepsMessage = new StepsMessage();
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();

        postMainStepsCourierCreation = new steps.couriercreation.PostMainSteps();
        postMainStepsCourierCreation.sendPostRequestCourier(TEST_LOGIN,TEST_PASSWORD,TEST_FIRST_NAME);
    }

    @AfterEach
    @DisplayName("Удаление курьера после создания и выполнения теста")
    public void deleteTestCourier() {
        postMainStepsCourierCreation.loginAndDeleteCourierIfExists();
    }

    @Test
    @DisplayName("Авторизация курьера с корректным логином и паролем")
    public void courierLogIn() {
        Response response =
                postMainSteps.sendPostRequestCourierLogin(
                        RestBaseClass.TEST_LOGIN,
                        RestBaseClass.TEST_PASSWORD);
                statusCodeSteps.checkSuccessfulCodeStatus200(response);
                stepsResponse.checkSuccessfulResponse(response);
                generalSteps.printResponseBodyToConsole(response);
    }

    @ParameterizedTest
    @MethodSource("logInUncorrectCredentialsProvider")
    @DisplayName("Попытка авторизации курьера с некорректным логином и паролем")
    public void courierLogInWithUncorrectFields(String login, String password) {
        Response response =
                postMainSteps.sendPostRequestCourierLogin(login, password);
                statusCodeSteps.checkCodeStatusWithErrorInFields404(response);
                stepsMessage.checkMessageWithErrorInFields(response);
                generalSteps.printResponseBodyToConsole(response);
    }

    @ParameterizedTest
    @MethodSource("logInEmptyCredentialsProvider")
    @DisplayName("Попытка авторизации курьера с пустыми значениями в логине и пароле")
    public void courierLogInWithEmptyFields(String login, String password) {
        Response response =
                postMainSteps.sendPostRequestCourierLogin(login, password);
                statusCodeSteps.checkNegativeStatusCodeWithoutRequiredFields400(response);
                stepsMessage.checkMessageWithoutRequiredFields(response);
                generalSteps.printResponseBodyToConsole(response);
    }

}
