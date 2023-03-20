package url;

import io.restassured.response.ValidatableResponse;

public class ApiIngredients extends BaseHttp {

    private static final String URIINGREDIENTS="api/ingredients";

    public ValidatableResponse getIngredients() {
        ValidatableResponse response = doGetRequest(URIINGREDIENTS);
        return response;
    }


}
