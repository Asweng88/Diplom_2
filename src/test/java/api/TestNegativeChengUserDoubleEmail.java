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
    private String mail1 = "test1Clubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String token;
    private String token1;


    @Before
    public void createUser(){

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);

        response = api.loginUser(mail, password);
        statusCode = response.extract().statusCode();
        token = response.extract().path("accessToken");
        assertEquals("Пользователь не залогинился", statusCode, HttpStatus.SC_OK);

        response = api.createUser(mail1, password, name);
        statusCode = response.extract().statusCode();
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);

        response = api.loginUser(mail1, password);
        statusCode = response.extract().statusCode();
        token1 = response.extract().path("accessToken");
        assertEquals("Пользователь не залогинился", statusCode, HttpStatus.SC_OK);
    }

    @Test
    public void validChengUser() {

        ValidatableResponse response = api.patchUser(mail1,password,name,token);
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
        int statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления пользователя", statusCode, HttpStatus.SC_ACCEPTED);

        response = api.deleteUser(token1);
        statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления пользователя", statusCode, HttpStatus.SC_ACCEPTED);
    }

}
