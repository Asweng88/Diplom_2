package url;

import io.restassured.response.ValidatableResponse;
import model.User;


public class ApiUserRegister extends BaseHttp {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";

    public ValidatableResponse createUser(String mail, String password, String name){
        User user = new User(mail,password,name);
        ValidatableResponse response = doPostRequest(baseUrl + "api/auth/register", user);
        return response;
    }

    public ValidatableResponse loginUser(String login, String password){
        User user = new User(login,password);
        ValidatableResponse response = doPostRequest(baseUrl + "api/auth/login", user);
        return response;
    }

    public ValidatableResponse deleteUser(String accessToken){
        ValidatableResponse response = doDeleteRequest(baseUrl + "api/auth/user",accessToken);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name, String accessToken){
        User user = new User(mail,password,name);
        ValidatableResponse response = doPatchRequest(baseUrl + "api/auth/user",accessToken, user);
        return response;
    }

    public ValidatableResponse patchUser(String mail, String password, String name){
        User user = new User(mail,password,name);
        ValidatableResponse response = doPatchRequest(baseUrl + "api/auth/user", user);
        return response;
    }

}
