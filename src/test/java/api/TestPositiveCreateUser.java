package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import url.ApiUserRegister;

import static org.junit.Assert.assertEquals;

public class TestPositiveCreateUser {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = "testClubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";

    @Test
    public void validCreateUser() {

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        assertEquals("Курьер не создан", statusCode, HttpStatus.SC_OK);
    }


    @After
    public void deleteUser() {

        ValidatableResponse response = api.loginUser(mail, password);
        int statusCode = response.extract().statusCode();
        String token = response.extract().path("accessToken");
        assertEquals("Курьер не создан", statusCode, HttpStatus.SC_OK);

        response = api.deleteUser(token);
        statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления курьера", statusCode, HttpStatus.SC_ACCEPTED);
    }


}
