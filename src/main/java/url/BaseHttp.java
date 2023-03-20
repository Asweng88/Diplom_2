package url;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseHttp {

    private static final String BASEURL = "https://stellarburgers.nomoreparties.site/";
    private static final String JSON = "application/json";

    protected ValidatableResponse doGetRequest(String uri) {
        return given()
                .header("Content-Type", JSON)
                .get(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doGetRequestAuthorization(String uri, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .get(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doPostRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doDeleteRequest(String uri, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .delete(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doPatchRequest(String uri, String accessToken, Object body) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .patch(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doPatchRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .patch(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doPostRequestAuthorization(String uri, Object body, String accessToken) {
        return given()
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .post(BASEURL + uri)
                .then();
    }

    protected ValidatableResponse doPostRequestNoAuthorization(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .body(body)
                .post(BASEURL + uri)
                .then();
    }
}
