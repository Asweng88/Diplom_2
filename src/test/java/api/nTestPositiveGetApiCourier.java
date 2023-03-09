//package api;
//
//
//import url.ApiCourier;
//import io.restassured.response.ValidatableResponse;
//import org.apache.http.HttpStatus;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class nTestPositiveGetApiCourier {
//
//    private final ApiCourier api = new ApiCourier();
//
//    ValidatableResponse response;
//    private String login = "testLoginJons";
//    private String password = "testPassword";
//    private String firstName = "testFirstNameJons";
//    private int id;
//
//    @Before
//
//    public void createCourier(){
//
//        response = api.createCourier(login,password,firstName);
//        int statusCode = response.extract().statusCode();
//        assertEquals("Курьер не создан",statusCode, HttpStatus.SC_CREATED);
//
//    }
//
//    @Test
//    public void validCreateCourier(){
//
//        response = api.loginCourier(login,password);
//        int statusCode = response.extract().statusCode();
//        id = response.extract().path("id");
//        assertEquals("Ошибка авторизации курьера",statusCode,HttpStatus.SC_OK);
//
//    }
//
//    @After
//    public void deleteCourier(){
//
//        ValidatableResponse response = api.deleteCourier(id);
//        int statusCode = response.extract().statusCode();
//        assertEquals("Ошибка удаления курьера",statusCode,HttpStatus.SC_OK);
//
//    }
//}
