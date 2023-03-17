package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import url.ApiUserRegister;

import static org.junit.Assert.*;

public class TestNegativeChengUserDoubleEmail {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = "testClubber@mail.ru";
    private String mailDouble = "test1Clubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String token;
    private String tokenDouble;

    @Before
    public void createUser() {
        ValidatableResponse response = api.createUser(mail, password, name);

        response = api.loginUser(mail, password);
        token = response.extract().path("accessToken");

        response = api.createUser(mailDouble, password, name);

        response = api.loginUser(mailDouble, password);
        tokenDouble = response.extract().path("accessToken");
    }

    @Test
    public void validChangeUser() {
        ValidatableResponse response = api.patchUser(mailDouble, password, name, token);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String body = response.extract().path("message");
        assertEquals("Пользователь не залогинился", statusCode, HttpStatus.SC_FORBIDDEN);
        assertFalse("Ошибка валидации success", success);
        assertEquals("Ошибка валидации", body, "User with such email already exists");
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = api.deleteUser(token);
        response = api.deleteUser(tokenDouble);
    }

}
