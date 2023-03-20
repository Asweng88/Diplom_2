package url;

import io.restassured.response.ValidatableResponse;
import model.Orders;

public class ApiOrders extends BaseHttp {

    private static final String URIORDERS = "api/orders";

    public ValidatableResponse createOrdersAuthorization(String[] ingredient, String accessToken) {
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestAuthorization(URIORDERS, orders, accessToken);
        return response;
    }

    public ValidatableResponse createOrdersNoAuthorization(String[] ingredient) {
        Orders orders = new Orders(ingredient);
        ValidatableResponse response = doPostRequestNoAuthorization(URIORDERS, orders);
        return response;
    }

    public ValidatableResponse getOrdersAuthorization(String accessToken) {
        ValidatableResponse response = doGetRequestAuthorization(URIORDERS, accessToken);
        return response;
    }

    public ValidatableResponse getOrdersNoAuthorization() {
        ValidatableResponse response = doGetRequest(URIORDERS);
        return response;
    }
}
