package api;

import url.ApiOrders;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class nTestPositivePostApiOrders {

    //Добавь необходимые поля
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Number rentTime;
    private final String deliveryDate;
    private final String comment;
    private final Object[] color;

    public nTestPositivePostApiOrders(String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, Object[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getPositivePostApiOrders() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                {"Naruto", "Udzumaki", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new Object[]{"BLACK ","GREY"}},
                {"Naruto", "Udzumaki", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new Object[]{"BLACK "}},
                {"Naruto", "Udzumaki", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new Object[]{"GREY"}},
                {"Naruto", "Udzumaki", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new Object[]{}},
        };
    }


    final ApiOrders api = new ApiOrders();


    @Test
    public void validCreateOrder(){

        ValidatableResponse response = api.createOrders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        int statusCode = response.extract().statusCode();
        int track = response.extract().path("track");
        assertEquals("Ошибка создания заявки",statusCode,HttpStatus.SC_CREATED);

    }

}
