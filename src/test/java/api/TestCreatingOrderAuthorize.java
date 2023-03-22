package api;

import org.junit.After;
import org.junit.Before;
import url.ApiIngredients;
import url.ApiOrders;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import url.ApiUserRegister;

import static org.junit.Assert.*;


public class TestCreatingOrderAuthorize {

    //Добавь необходимые поля
    private String mail = "testClubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String token;
    private String ingredient1;
    private String ingredient2;

    private ApiUserRegister api = new ApiUserRegister();

    @Before
    public void createUser() {
        ApiIngredients ingredients = new ApiIngredients();
        ValidatableResponse response = api.createUser(mail, password, name);
        token = response.extract().path("accessToken");

        response = ingredients.getIngredients();
        ingredient1 = response.extract().path("data[0]._id").toString();
        ingredient2 = response.extract().path("data[1]._id").toString();
    }

    @Test
    public void creatingOrderAuthorize() {
        ApiOrders orders = new ApiOrders();
        ValidatableResponse response = orders.createOrdersAuthorization(new String[]{ingredient1, ingredient2}, token);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String name = response.extract().path("name");
        int orderNumber = response.extract().path("order.number");
        assertEquals("Ошибка создания заявки", statusCode, HttpStatus.SC_OK);
        assertNotNull("Ошибка валидации", name);
        assertNotNull("Ошибка валидации", orderNumber);
        assertTrue("Ошибка валидации", success);
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = api.deleteUser(token);

    }

}
