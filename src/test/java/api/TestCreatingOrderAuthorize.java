package api;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import url.ApiIngredients;
import url.ApiOrders;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import url.ApiUserRegister;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TestCreatingOrderAuthorize {

    //Добавь необходимые поля
    private String mail = "testClubber@mail.ru";;
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
        int statusCode = response.extract().statusCode();
        token = response.extract().path("accessToken");
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);

        response = ingredients.getIngredients();
        statusCode = response.extract().statusCode();
        ingredient1 = response.extract().path("data[0]._id").toString();
        ingredient2 = response.extract().path("data[1]._id").toString();
        assertEquals("Список ингредиентов не получен", statusCode, HttpStatus.SC_OK);
    }

    @Test
    public void creatingOrderAuthorize() {
        ApiOrders orders = new ApiOrders();
        ValidatableResponse response = orders.createOrdersAuthorization(new Object[]{ingredient1, ingredient2},token);
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
        ValidatableResponse response = api.loginUser(mail, password);
        token = response.extract().path("accessToken");
        response = api.deleteUser(token);
        int statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления пользователя", statusCode, HttpStatus.SC_ACCEPTED);
    }

}
