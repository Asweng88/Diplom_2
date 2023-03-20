package url;

import io.restassured.response.ValidatableResponse;
import model.User;


public class ApiUserRegister extends BaseHttp {

    private static final String URIUSERLOGIN = "api/auth/login";
    private static final String URIUSERREGISTER = "api/auth/register";
    private static final String URIUSERCHANGE = "api/auth/user";

    public ValidatableResponse createUser(String mail, String password, String name) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPostRequest(URIUSERREGISTER, user);
        return response;
    }

    public ValidatableResponse loginUser(String login, String password) {
        User user = new User(login, password);
        ValidatableResponse response = doPostRequest(URIUSERLOGIN, user);
        return response;
    }

    public ValidatableResponse deleteUser(String accessToken) {
        ValidatableResponse response = doDeleteRequest(URIUSERCHANGE, accessToken);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name, String accessToken) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPatchRequest(URIUSERCHANGE, accessToken, user);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name) {
        User user = new User(mail, password, name);
        ValidatableResponse response = doPatchRequest(URIUSERCHANGE, user);
        return response;
    }

}
