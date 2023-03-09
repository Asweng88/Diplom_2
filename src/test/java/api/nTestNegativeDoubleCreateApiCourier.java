//package api;
//
//import url.ApiCourier;
//import io.restassured.response.ValidatableResponse;
//import org.apache.http.HttpStatus;
//import org.junit.After;
//import org.junit.Test;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class nTestNegativeDoubleCreateApiCourier {
//
//    final ApiCourier api = new ApiCourier();
//    String login = "testLoginJons";
//    String password = "testPassword";
//    String firstName = "testFirstNameJons";
//    String newPassword = "testPassword2";
//    String newFirstName = "testFirstNameJons2";
//
//    @Test
//    public void doubleCreateCourier(){
//
//        ValidatableResponse response = api.createCourier(login,password,firstName);
//        int statusCode = response.extract().statusCode();
//        boolean bodyPositive = response.extract().path("ok");
//        assertEquals("Курьер не создан",statusCode, HttpStatus.SC_CREATED);
//        assertTrue("Курьер не создан",bodyPositive);
//
//        response = api.createCourier(login,password,firstName);
//        statusCode = response.extract().statusCode();
//        String bodyNegative = response.extract().path("message");
//        assertEquals("Ошибка валидации", statusCode, 409);
//        assertEquals("Ошибка валидации", bodyNegative, "Этот логин уже используется");
//
//    }
//
//    @Test
//    public void doubleLoginCreateCourier(){
//
//        ValidatableResponse response = api.createCourier(login,password,firstName);
//        int statusCode = response.extract().statusCode();
//        boolean bodyPositive = response.extract().path("ok");
//        assertEquals("Курьер не создан",statusCode, HttpStatus.SC_CREATED);
//        assertTrue("Курьер не создан",bodyPositive);
//
//        response = api.createCourier(login,newPassword,newFirstName);
//        statusCode = response.extract().statusCode();
//        String bodyNegative = response.extract().path("message");
//        assertEquals("Ошибка валидации", statusCode, 409);
//        assertEquals("Ошибка валидации", bodyNegative, "Этот логин уже используется");
//
//    }
//    @After
//    public void deleteCourier(){
//
//        ValidatableResponse response = api.loginCourier(login,password);
//        int statusCode = response.extract().statusCode();
//        int id = response.extract().path("id");
//        assertEquals("Ошибка авторизации курьера",statusCode,HttpStatus.SC_OK);
//
//        response = api.deleteCourier(id);
//        statusCode = response.extract().statusCode();
//        boolean bodyPositive = response.extract().path("ok");
//        assertEquals("Ошибка удаления курьера",statusCode,HttpStatus.SC_OK);
//
//    }
//
//}
