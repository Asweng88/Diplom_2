package api;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import url.ApiUserRegister;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class TestPositiveChengUserAuthorize {

    private final ApiUserRegister api = new ApiUserRegister();

    private String mail = "testClubber@mail.ru";
    private String password = "testPassword";
    private String name = "clubber";
    private String mailCheng;
    private String passwordCheng;
    private String nameCheng;
    private String token;

    public TestPositiveChengUserAuthorize(String mailCheng, String passwordCheng, String nameCheng) {
        this.mailCheng = mailCheng;
        this.passwordCheng = passwordCheng;
        this.nameCheng = nameCheng;
    }

    @Parameterized.Parameters
    public static Object[][] getCreateUser() {
        //Сгенерируй тестовые данные
        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(10) + "@mail.ru", "testPassword", "clubber"},
                {"testclubber@mail.ru", RandomStringUtils.randomAlphabetic(10), "clubber"},
                {"testclubber@mail.ru", "testPassword", RandomStringUtils.randomAlphabetic(10)},
        };
    }

    @Before
    public void createUser() {

        ValidatableResponse response = api.createUser(mail, password, name);

        response = api.loginUser(mail, password);
        token = response.extract().path("accessToken");


    }

    @Test
    public void validChengUser() {

        ValidatableResponse response = api.patchUser(mailCheng, passwordCheng, nameCheng, token);
        int statusCode = response.extract().statusCode();
        Boolean success = response.extract().path("success");
        String responseEmail = response.extract().path("user.email").toString();
        String responseName = response.extract().path("user.name").toString();
        assertEquals("Пользователь не залогинился", statusCode, HttpStatus.SC_OK);
        assertTrue("Ошибка валидации success", success);
        assertEquals("Ошибка валидации responseEmail", responseEmail, mailCheng.toLowerCase());
        assertEquals("Ошибка валидации responseName", responseName, nameCheng);

    }

    @After
    public void deleteUser() {
        ValidatableResponse response = api.deleteUser(token);
    }

}
