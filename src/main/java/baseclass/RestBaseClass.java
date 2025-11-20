package baseclass;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class RestBaseClass {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    // Эндпоинты
    public String apiCourierLoginInSystem = "/api/v1/courier/login";
    public String apiCourierCreation = "/api/v1/courier";
    public String apiCourierDelete = "/api/v1/courier/";
    public String apiOrderCreate = "/api/v1/orders";

    // Тестовые данные для авторизации CourierLoginTest
    protected static final String TEST_LOGIN = "AuthTestCourier";
    protected static final String TEST_PASSWORD = "AuthTestPassword";
    protected static final String TEST_FIRST_NAME = "AuthTester";


    /*Методы для параметризации класса CourierCreationTest*/
    private static Stream<Arguments> creationUserCredentialsProvider() {
        return Stream.of(
                Arguments.of(TEST_LOGIN, "", TEST_FIRST_NAME),
                Arguments.of("", TEST_PASSWORD, TEST_FIRST_NAME)
        );
    }

    /*Методы для параметризации класса CourierLoginTest*/
    private static Stream<Arguments> logInCorrectCredentialsProvider() {
        return Stream.of(
                Arguments.of(TEST_LOGIN, TEST_PASSWORD)
        );
    }

    private static Stream<Arguments> logInUncorrectCredentialsProvider() {
        return Stream.of(
                Arguments.of(TEST_LOGIN + 1, TEST_PASSWORD),
                Arguments.of(TEST_LOGIN, TEST_PASSWORD + 1)
        );
    }

    private static Stream<Arguments> logInEmptyCredentialsProvider() {
        return Stream.of(
                Arguments.of("", TEST_PASSWORD),
                Arguments.of(TEST_LOGIN, ""),
                Arguments.of("", "")
        );
    }

    /*Методы для параметризации класса OrderCreationTest*/
    private static Stream<Arguments> orderCreationCredentialsProvider() {
        return Stream.of(
                Arguments.of("Антон", "Иванов", "Красная площадь, 1", 5, "+79150006219", 3, "2025-11-20", "Позвонить за час до доставки", new String[] {"BLACK"}),
                Arguments.of("Анастасия", "Забелин", "Пушкина, дом Колотушкина, 1", 10, "89265256677", 1, "2025-12-01", "Хочу самый мощный", new String[] {"GREY"}),
                Arguments.of("Сергей", "Махортов", "Поселок копатыча, д.38", 1, "+70000000000", 5, "2025-11-18", "И побыстрей!!!", new String[] {"BLACK", "GREY"}),
                Arguments.of("Вячеслав", "Андреев", "Зеленая роща", 20, "88005553535", 7, "2026-01-01", "Не звонить", new String[] {""})
        );
    }



}
