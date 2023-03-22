package api;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import url.ApiUserRegister;

import static org.junit.Assert.*;

public class TestNegativeDoubleCreateUser {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";;
    private String password = RandomStringUtils.randomAlphabetic(10);
    private String name = RandomStringUtils.randomAlphabetic(10);
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
    }

}
