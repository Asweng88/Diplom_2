package url;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseHttp {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String JSON = "application/json";

    protected ValidatableResponse doGetRequest(String uri) {
        return given()
                .header("Content-Type", JSON)
                .get(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doGetRequestAuthorization(String uri, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .get(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doPostRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doDeleteRequest(String uri, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .delete(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doPatchRequest(String uri, String accessToken, Object body) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .patch(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doPatchRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .patch(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doPostRequestAuthorization(String uri, Object body, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .post(BASE_URL + uri)
                .then();
    }

    protected ValidatableResponse doPostRequestNoAuthorization(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(BASE_URL + uri)
                .then();
    }
}
