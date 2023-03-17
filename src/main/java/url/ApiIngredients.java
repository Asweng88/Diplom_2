package url;

import io.restassured.response.ValidatableResponse;

public class ApiIngredients extends BaseHttp {

    public ValidatableResponse getIngredients() {
        ValidatableResponse response = doGetRequest("api/ingredients");
        return response;
    }


}
