package url;


import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseHttp {

    private final String JSON = "application/json";

    protected ValidatableResponse doGetRequest(String uri){
        return given()
                .header("Content-Type", JSON)
                .get(uri)
                .then();
    }

    protected ValidatableResponse doPostRequest(String uri, Object body){
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(uri)
                .then();
    }

    protected ValidatableResponse doDeleteRequest(String uri, String accessToken){
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .delete(uri)
                .then();
    }

}
