package Steps.General;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GeneralSteps {

    @Step("Вывод ответа в консоль")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }

}
