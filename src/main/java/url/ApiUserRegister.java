package url;

import io.restassured.response.ValidatableResponse;
import model.User;


public class ApiUserRegister extends BaseHttp {

    private static final String URI_USER_LOGIN = "api/auth/login";
    private static final String URI_USER_REGISTER = "api/auth/register";
    private static final String URI_USER_CHANGE = "api/auth/user";

    public ValidatableResponse createUser(String mail, String password, String name) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPostRequest(URI_USER_REGISTER, user);
        return response;
    }

    public ValidatableResponse loginUser(String login, String password) {
        User user = new User(login, password);
        ValidatableResponse response = doPostRequest(URI_USER_LOGIN, user);
        return response;
    }

    public ValidatableResponse deleteUser(String accessToken) {
        ValidatableResponse response = doDeleteRequest(URI_USER_CHANGE, accessToken);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name, String accessToken) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPatchRequest(URI_USER_CHANGE, accessToken, user);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPatchRequest(URI_USER_CHANGE, user);
        return response;
    }

}
