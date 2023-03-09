package url;

import io.restassured.response.ValidatableResponse;
import model.Orders;


public class ApiOrders extends BaseHttp {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";

    public ValidatableResponse createOrdersAuthorization(Object[] ingredient, String accessToken){
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestAuthorization(baseUrl + "api/orders", orders, accessToken);
        return response;
    }

    public ValidatableResponse createOrdersNoAuthorization(Object[] ingredient){
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestNoAuthorization(baseUrl + "api/orders", orders);
        return response;
    }

    public ValidatableResponse getOrdersAuthorization(String accessToken){
        ValidatableResponse response = doGetRequestAuthorization(baseUrl + "api/orders", accessToken);
        return response;
    }

    public ValidatableResponse getOrdersNoAuthorization(){
        ValidatableResponse response = doGetRequest(baseUrl + "api/orders");
        return response;
    }
}
