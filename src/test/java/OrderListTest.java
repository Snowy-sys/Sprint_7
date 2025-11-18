import Steps.General.GeneralSteps;
import Steps.General.StatusCodeSteps;
import Steps.OrderList.GetMainSteps;
import Steps.OrderList.StepsResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderListTest extends GetMainSteps {

    private StepsResponse stepsResponse;
    private GeneralSteps generalSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    public void initStepsConstructors(){
        stepsResponse = new StepsResponse();
        generalSteps = new GeneralSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @Test
    @DisplayName("Получение списка всех заказов")
    public void listOfAllOrders(){
        Response response = sendGetRequestListOrder();
        statusCodeSteps.checkSuccessfulCodeStatus200(response);
        stepsResponse.checkSuccessfulResponse(response);
        generalSteps.printResponseBodyToConsole(response);
    }
}
