package url;

import io.restassured.response.ValidatableResponse;

public class ApiIngredients extends BaseHttp {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";

    public ValidatableResponse getIngredients(){
        ValidatableResponse response = doGetRequest(baseUrl + "api/ingredients");
        return response;
    }


}
