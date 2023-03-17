package api;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import url.ApiOrders;
import url.ApiUserRegister;

import static org.junit.Assert.*;


public class TestGetOrderAuthorize {

    //Добавь необходимые поля
    private String mail = RandomStringUtils.random(10) + "@mail.ru";;
    private String password = RandomStringUtils.random(10);
    private String name = RandomStringUtils.random(10);
    private String token;

    private ApiUserRegister api = new ApiUserRegister();

    @Before
    public void createUser() {
        ValidatableResponse response = api.createUser(mail, password, name);
        token = response.extract().path("accessToken");
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
        ValidatableResponse response = api.deleteUser(token);
    }

}
