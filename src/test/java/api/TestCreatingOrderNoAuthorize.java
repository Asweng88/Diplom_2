package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import url.ApiIngredients;
import url.ApiOrders;
import url.ApiUserRegister;

import static org.junit.Assert.*;

public class TestCreatingOrderNoAuthorize {
    //Добавь необходимые поля
    private String ingredient1;
    private String ingredient2;
    private ApiUserRegister api = new ApiUserRegister();

    @Before
    public void before() {
        ApiIngredients ingredients = new ApiIngredients();

        ValidatableResponse response  = ingredients.getIngredients();
        ingredient1 = response.extract().path("data[0]._id").toString();
        ingredient2 = response.extract().path("data[1]._id").toString();
    }

    @Test
    public void creatingOrderNoAuthorize() {
        ApiOrders orders = new ApiOrders();
        ValidatableResponse response = orders.createOrdersNoAuthorization(new String[]{ingredient1, ingredient2});
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String name = response.extract().path("name");
        int orderNumber = response.extract().path("order.number");
        assertEquals("Ошибка создания заявки", statusCode, HttpStatus.SC_OK);
        assertNotNull("Ошибка валидации", name);
        assertNotNull("Ошибка валидации", orderNumber);
        assertTrue("Ошибка валидации", success);
    }

}
