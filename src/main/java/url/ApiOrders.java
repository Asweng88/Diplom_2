package url;

import io.restassured.response.ValidatableResponse;
import model.Orders;


public class ApiOrders extends BaseHttp {

    private final String baseUrl = "https://qa-scooter.praktikum-services.ru/";

    public ValidatableResponse createOrders(String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, Object[] color){
        Orders orders = new Orders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = doPostRequest(baseUrl + "api/v1/orders", orders);
        return response;
    }

    public ValidatableResponse getOrders(){
        ValidatableResponse response = doGetRequest(baseUrl + "api/v1/orders");
        return response;
    }


}
