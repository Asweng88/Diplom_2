package url;

import io.restassured.response.ValidatableResponse;

public class ApiIngredients extends BaseHttp {

    private static final String URI_INGREDIENTS="api/ingredients";

    public ValidatableResponse getIngredients() {
        ValidatableResponse response = doGetRequest(URI_INGREDIENTS);
        return response;
    }


}
