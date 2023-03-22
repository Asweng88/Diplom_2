package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import url.ApiUserRegister;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class TestPositiveCreateUser {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = "testclubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String token;

    @Test
    public void validCreateUser() {

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        token = response.extract().path("accessToken");
        String refreshToken = response.extract().path("refreshToken");
        String responseEmail = response.extract().path("user.email");
        String responseName = response.extract().path("user.name");
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);
        assertTrue("Ошибка валидации success", success);
        assertNotNull("Token не должен быть равен null", token);
        assertNotNull("RefreshToken не должен быть равен null", refreshToken);
        assertEquals("Ошибка валидации responseEmail", responseEmail, mail);
        assertEquals("Ошибка валидации responseName", responseName, name);

    }


    @After
    public void deleteUser() {
        ValidatableResponse response = api.deleteUser(token);
    }


}
