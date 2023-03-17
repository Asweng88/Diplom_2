package url;

import io.restassured.response.ValidatableResponse;
import model.User;


public class ApiUserRegister extends BaseHttp {

    private final String uriUserChange = "api/auth/user";

    public ValidatableResponse createUser(String mail, String password, String name) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPostRequest("api/auth/register", user);
        return response;
    }

    public ValidatableResponse loginUser(String login, String password) {
        User user = new User(login, password);
        ValidatableResponse response = doPostRequest("api/auth/login", user);
        return response;
    }

    public ValidatableResponse deleteUser(String accessToken) {
        ValidatableResponse response = doDeleteRequest(uriUserChange, accessToken);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name, String accessToken) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPatchRequest(uriUserChange, accessToken, user);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPatchRequest(uriUserChange, user);
        return response;
    }

}
