package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import url.ApiOrders;
import url.ApiUserRegister;

import static org.junit.Assert.*;


public class TestGetOrderNoAuthorize {

    //Добавь необходимые поля

    private ApiUserRegister api = new ApiUserRegister();

    @Test
    public void creatingOrderNoAuthorize() {
        ApiOrders orders = new ApiOrders();
        ValidatableResponse response = orders.getOrdersNoAuthorization();
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        assertEquals("Ошибка валидации", statusCode, HttpStatus.SC_UNAUTHORIZED);
        assertFalse("Ошибка валидации", success);
    }

}
