package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import url.ApiUserRegister;

import static org.junit.Assert.*;

public class TestNegativeDoubleCreateUser {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = "testClubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String token;
    @Test
    public void doubleCreateUser(){

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        token = response.extract().path("accessToken");
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);

        response = api.createUser(mail, password, name);
        statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String bodyNegative = response.extract().path("message");
        assertEquals("Ошибка валидации", statusCode, HttpStatus.SC_FORBIDDEN);
        assertEquals("Ошибка валидации", bodyNegative, "User already exists");
        assertFalse("Ошибка валидации", success);

    }

    @After
    public void deleteUser(){

        ValidatableResponse response = api.deleteUser(token);
        int statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления пользователя", statusCode, HttpStatus.SC_ACCEPTED);
    }

}
