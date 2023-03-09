package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import url.ApiIngredients;
import url.ApiOrders;
import url.ApiUserRegister;

import static org.junit.Assert.*;


public class TestGetOrderAuthorize {

    //Добавь необходимые поля
    private String mail = "testClubber@mail.ru";;
    private String password = "testPassword";
    private String name = "clubber";
    private String token;

    private ApiUserRegister api = new ApiUserRegister();

    @Before
    public void createUser() {

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        token = response.extract().path("accessToken");
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);

    }

    @Test
    public void creatingOrderAuthorize() {
        ApiOrders orders = new ApiOrders();
        ValidatableResponse response = orders.getOrdersAuthorization(token);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        assertEquals("Ошибка получения списка заявок", statusCode, HttpStatus.SC_OK);
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
