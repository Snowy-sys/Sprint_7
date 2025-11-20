import baseclass.RestBaseClass;
import steps.general.GeneralSteps;
import steps.general.StatusCodeSteps;
import steps.orderlist.GetMainSteps;
import steps.orderlist.StepsResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderListTest extends RestBaseClass {

    private GetMainSteps getMainSteps;
    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    @DisplayName("Инициализация конструкторов с шагами тестов")
    public void initStepsConstructors(){
        getMainSteps = new GetMainSteps();
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @Test
    @DisplayName("Получение списка всех заказов")
    public void listOfAllOrders(){
        Response response =
                getMainSteps.sendGetRequestListOrder();
                statusCodeSteps.checkSuccessfulCodeStatus200(response);
                stepsResponse.checkSuccessfulResponse(response);
                generalSteps.printResponseBodyToConsole(response);
    }
}
