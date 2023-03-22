package api;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import url.ApiUserRegister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class TestNegativeRequiredFieldsCreateUser {

    //Добавь необходимые поля
    private final String mail;
    private final String password;
    private final String name;
    private final int code;
    private final String messages;

    public TestNegativeRequiredFieldsCreateUser(String mail, String password, String name, int code, String messages) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.code = code;
        this.messages = messages;
    }

        @Parameterized.Parameters
        public static Object[][] getNegativeCreateUser() {
            //Сгенерируй тестовые данные
            return new Object[][] {
                    {null, "testPassword", "clubber", 403, "Email, password and name are required fields"},
                    {"testClubber@mail.ru", null, "clubber", 403, "Email, password and name are required fields"},
                    {"testClubber@mail.ru", "testPassword", null, 403, "Email, password and name are required fields"},
            };
        }

    private final ApiUserRegister api = new ApiUserRegister();

    @Test
    public void requiredFieldsCreateUser(){

        ValidatableResponse response = api.createUser(mail, password, name);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String body = response.extract().path("message");
        assertEquals("Ошибка валидации", statusCode, code);
        assertEquals("Ошибка валидации", body, messages);
        assertFalse("Ошибка валидации", success);
        if (statusCode==200){
            String token = response.extract().path("accessToken");
            response = api.deleteUser(token);
        }
    }
}
