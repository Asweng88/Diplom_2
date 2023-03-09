package api;

import io.qameta.allure.junit4.DisplayName;
import url.ApiOrders;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class nTestGetApiOrders {

    final ApiOrders api = new ApiOrders();

    @Test
    @DisplayName("Check order list")
    public void validCreateOrder(){

        ValidatableResponse response = api.getOrders();
        int statusCode = response.extract().statusCode();
        Object orders = response.extract().path("orders");
        assertEquals("Ошибка получения заявок",statusCode,HttpStatus.SC_OK);
        assertTrue("Ответ не является списком",orders instanceof ArrayList);

    }

}
