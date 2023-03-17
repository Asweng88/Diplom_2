package url;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseHttp {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";

    private final String JSON = "application/json";

    protected ValidatableResponse doGetRequest(String uri) {
        return given()
                .header("Content-Type", JSON)
                .get(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doGetRequestAuthorization(String uri, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .get(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doPostRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doDeleteRequest(String uri, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .delete(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doPatchRequest(String uri, String accessToken, Object body) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .patch(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doPatchRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .patch(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doPostRequestAuthorization(String uri, Object body, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .post(baseUrl + uri)
                .then();
    }

    protected ValidatableResponse doPostRequestNoAuthorization(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(baseUrl + uri)
                .then();
    }
}
