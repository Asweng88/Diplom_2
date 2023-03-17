package url;

import io.restassured.response.ValidatableResponse;
import model.Orders;

public class ApiOrders extends BaseHttp {

    private static String uriOrders = "api/orders";

    public ValidatableResponse createOrdersAuthorization(String[] ingredient, String accessToken) {
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestAuthorization(uriOrders, orders, accessToken);
        return response;
    }

    public ValidatableResponse createOrdersNoAuthorization(String[] ingredient) {
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestNoAuthorization(uriOrders, orders);
        return response;
    }

    public ValidatableResponse getOrdersAuthorization(String accessToken) {
        ValidatableResponse response = doGetRequestAuthorization(uriOrders, accessToken);
        return response;
    }

    public ValidatableResponse getOrdersNoAuthorization() {
        ValidatableResponse response = doGetRequest(uriOrders);
        return response;
    }
}
