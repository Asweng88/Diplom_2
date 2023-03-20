package url;

import io.restassured.response.ValidatableResponse;
import model.Orders;

public class ApiOrders extends BaseHttp {

    private static final String URI_ORDERS = "api/orders";

    public ValidatableResponse createOrdersAuthorization(String[] ingredient, String accessToken) {
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestAuthorization(URI_ORDERS, orders, accessToken);
        return response;
    }

    public ValidatableResponse createOrdersNoAuthorization(String[] ingredient) {
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestNoAuthorization(URI_ORDERS, orders);
        return response;
    }

    public ValidatableResponse getOrdersAuthorization(String accessToken) {
        ValidatableResponse response = doGetRequestAuthorization(URI_ORDERS, accessToken);
        return response;
    }

    public ValidatableResponse getOrdersNoAuthorization() {
        ValidatableResponse response = doGetRequest(URI_ORDERS);
        return response;
    }
}
