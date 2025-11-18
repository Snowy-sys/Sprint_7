import Steps.General.GeneralSteps;
import Steps.General.StatusCodeSteps;
import Steps.OrderCreation.PostMainSteps;
import Steps.OrderCreation.StepsResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class OrderCreationTest extends PostMainSteps {



    private static Stream<Arguments> positiveCredentialsProvider() {
        return Stream.of(
                Arguments.of("Антон", "Иванов", "Красная площадь, 1", 5, "+79150006219", 3, "2025-11-20", "Позвонить за час до доставки", new String[] {"BLACK"}),
                Arguments.of("Анастасия", "Забелин", "Пушкина, дом Колотушкина, 1", 10, "89265256677", 1, "2025-12-01", "Хочу самый мощный", new String[] {"GREY"}),
                Arguments.of("Сергей", "Махортов", "Поселок копатыча, д.38", 1, "+70000000000", 5, "2025-11-18", "И побыстрей!!!", new String[] {"BLACK", "GREY"}),
                Arguments.of("Вячеслав", "Андреев", "Зеленая роща", 20, "88005553535", 7, "2026-01-01", "Не звонить", new String[] {""})
        );
    }

    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    public void initStepsConstructors(){
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @ParameterizedTest
    @MethodSource("positiveCredentialsProvider")
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

        Response response = sendPostRequestOrderCreation(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        statusCodeSteps.checkSuccessfulCodeStatus201(response);
        stepsResponse.checkSuccessfulResponse(response);
        generalSteps.printResponseBodyToConsole(response);
    }

}
