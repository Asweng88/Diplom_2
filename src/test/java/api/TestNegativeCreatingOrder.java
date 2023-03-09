package api;


import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import url.ApiOrders;
import url.ApiUserRegister;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestNegativeCreatingOrder {

    //Добавь необходимые поля
    private final int code;
    private final String messages;
    private final Object[] ingredient;

    public TestNegativeCreatingOrder(Object[] ingredient, int code, String messages) {
        this.ingredient = ingredient;
        this.code = code;
        this.messages = messages;
    }

    @Parameterized.Parameters
    public static Object[][] getNegativeCreateUser() {
        //Сгенерируй тестовые данные
        return new Object[][]{
                {new Object[]{"rerwfds"}, 500, ""},
                {new Object[]{}, 400, "Ingredient ids must be provided"},
                {new Object[]{"61c0c5a71d1f82001bdaaa6d ", "asdasdasd"}, 500, ""},
        };
    }

    @Test
    public void creatingOrderNegative() {
        ApiOrders orders = new ApiOrders();
        ValidatableResponse response = orders.createOrdersNoAuthorization(ingredient);
        int statusCode = response.extract().statusCode();

        if (statusCode == 500) {
            assertEquals("Ошибка валидации", statusCode, code);
        } else {
            Boolean success = response.extract().path("success");
            String body = response.extract().path("message");
            assertEquals("Ошибка валидации", body, messages);
            assertFalse("Ошибка валидации", success);
        }
    }

}
