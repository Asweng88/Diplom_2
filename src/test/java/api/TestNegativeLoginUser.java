package api;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import url.ApiUserRegister;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestNegativeLoginUser {
    //Добавь необходимые поля
    private final String mailFirst;
    private final String mailSecond = "testClubber@mail.ru";
    private final String passwordFirst;
    private final String passwordSecond = "testPassword";
    private final String name = "clubber";
    private final int code;
    private final String messages;
    private String token;

    public TestNegativeLoginUser(String mail, String password, int code, String messages) {
        this.mailFirst = mail;
        this.passwordFirst = password;

        this.code = code;
        this.messages = messages;
    }

    @Parameterized.Parameters
    public static Object[][] getNegativeCreateUser() {
        //Сгенерируй тестовые данные
        return new Object[][]{
                {"testClubber1@mail.ru", "testPasswords", 401, "email or password are incorrect"},
                {null, "testPassword", 401, "email or password are incorrect"},
                {"testClubber@mail.ru", "null", 401, "email or password are incorrect"},
                {"testClubber@mail.ru", null, 401, "email or password are incorrect"},
        };
    }

    private final ApiUserRegister api = new ApiUserRegister();

    @Before
    public void createUser() {
        ValidatableResponse response = api.createUser(mailSecond, passwordSecond, name);
        token = response.extract().path("accessToken");
    }

    @Test
    public void requiredFieldsLoginUser() {
        ValidatableResponse response = api.loginUser(mailFirst, passwordFirst);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String body = response.extract().path("message");
        assertEquals("Ошибка валидации", statusCode, code);
        assertEquals("Ошибка валидации", body, messages);
        assertFalse("Ошибка валидации", success);
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = api.deleteUser(token);
    }

}
