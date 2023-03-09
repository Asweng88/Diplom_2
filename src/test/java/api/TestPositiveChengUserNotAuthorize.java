package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import url.ApiUserRegister;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class TestPositiveChengUserNotAuthorize {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = "testClubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String mailCheng;
    private String passwordCheng;
    private String nameCheng;
    private String token;

    public TestPositiveChengUserNotAuthorize(String mailCheng, String passwordCheng, String nameCheng) {
        this.mailCheng = mailCheng;
        this.passwordCheng = passwordCheng;
        this.nameCheng = nameCheng;
    }

    @Parameterized.Parameters
    public static Object[][] getCreateUser() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                {"testclubber12223@mail.ru", "testPassword", "clubber"},
                {"testclubber@mail.ru", "testPassword1", "clubber"},
                {"testclubber@mail.ru", "testPassword", "clubber1"},
        };
    }

    @Before
    public void createUser(){

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        assertEquals("Пользователь не создан", statusCode, HttpStatus.SC_OK);

    }

    @Test
    public void validChengUser() {

        ValidatableResponse response = api.patchUser(mailCheng,passwordCheng,nameCheng);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String body = response.extract().path("message");
        assertEquals("Пользователь не залогинился", statusCode, HttpStatus.SC_UNAUTHORIZED);
        assertFalse("Ошибка валидации success", success);
        assertEquals("Ошибка валидации", body, "You should be authorised");
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = api.loginUser(mail, password);
        token = response.extract().path("accessToken");

        response = api.deleteUser(token);
        int statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления пользователя", statusCode, HttpStatus.SC_ACCEPTED);
    }

}
